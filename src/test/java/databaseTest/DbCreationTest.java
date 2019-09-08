package databaseTest;

import com.metroInformationSystem.Main;
import com.metroInformationSystem.domain.*;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.domain.enums.ReplenishmentMethod;
import com.metroInformationSystem.domain.enums.Transport;
import com.metroInformationSystem.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DbCreationTest {

    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private PaymentLocationRepo paymentLocationRepo;
    @Autowired
    private GroundTransportationRepo groundTransportationRepo;
    @Autowired
    private StationRepo stationRepo;
    @Autowired
    private FixationOfPassageRepo fixationOfPassageRepo;
    @Autowired
    private TravelCardRepo travelCardRepo;
    @Autowired
    private ReplenishmentTypeRepo replenishmentTypeRepo;
    @Autowired
    private ReplenishmentCardRepo replenishmentCardRepo;


    @Before
    @Rollback(false)
    public void setUpDB() {
        Iterable<PaymentLocation> locations = setUpPaymentLocation();
        Iterable<Passenger> passengers = setUpPassengers();
        Iterable<GroundTransportation> groundTransportation = setUpGroundTransportation(locations);
        Iterable<Station> stations = setUpStation(locations);
        Iterable<TravelCard> cards = setUpTravelCard();
        Iterable<FixationOfPassage> fixations = setUpFixationOfPassage(cards, locations);
        Iterable<ReplenishmentType> replenishmentTypes = setUpReplenishmentType(stations);

        Iterable<ReplenishmentCard> replenishmentCards =
                setUpReplenishmentCard((List<TravelCard>) cards,(List <ReplenishmentType>) replenishmentTypes);
    }

    private Iterable<Passenger> setUpPassengers(){
        Passenger pasha = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger asdas = new Passenger("Полищук", "Евгений", "Александрович");

        return passengerRepo.saveAll(Arrays.asList(pasha, asdas));
    }

    private Iterable<PaymentLocation> setUpPaymentLocation(){

        PaymentLocation turnstile1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation validator1 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation validator2 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation turnstile2 = new PaymentLocation(CheckerPayment.TURNSTILE);

        return paymentLocationRepo.saveAll(Arrays.asList(validator1, validator2, turnstile1, turnstile2));
    }

    private Iterable<GroundTransportation> setUpGroundTransportation(Iterable<PaymentLocation> locations){

        // TODO: нужно ограничение не дающие записывать в качестве места оплаты турникет метро
        GroundTransportation bus = new GroundTransportation(((List<PaymentLocation>) locations).get(2), Transport.BUS, "30" );
        GroundTransportation tram = new GroundTransportation(((List<PaymentLocation>) locations).get(0), Transport.TRAM, "64");

        return groundTransportationRepo.saveAll(Arrays.asList(bus, tram));
    }

    private Iterable<Station> setUpStation(Iterable<PaymentLocation> locations){

        // TODO: нужно ограничение не дающие записывать в качестве места оплаты валидатор наземного транспорта
        Station ladka = new Station(((List<PaymentLocation>)locations).get(1), "Ладожская");
        Station novochera = new Station(((List<PaymentLocation>) locations).get(3), "Новочеркасская");

        return stationRepo.saveAll(Arrays.asList(ladka, novochera));
    }

    private Iterable<TravelCard> setUpTravelCard(){

        TravelCard card1 = new TravelCard();
        TravelCard card2 = new TravelCard();

        return travelCardRepo.saveAll(Arrays.asList(card1, card2));
    }

    private Iterable<FixationOfPassage> setUpFixationOfPassage(Iterable<TravelCard> cards, Iterable<PaymentLocation> locations){

        FixationOfPassage firstFixCard1 = new FixationOfPassage(
                ((List<TravelCard>) cards).get(0),
                ((List<PaymentLocation>) locations).get(2),
                new java.sql.Date(new java.util.Date().getTime()));

        FixationOfPassage firstFixCard2 = new FixationOfPassage(
                ((List<TravelCard>) cards).get(1),
                ((List<PaymentLocation>) locations).get(0),
                new java.sql.Date(new java.util.Date().getTime()));

        FixationOfPassage secondFixCard1 = new FixationOfPassage(
                ((List<TravelCard>) cards).get(0),
                ((List<PaymentLocation>) locations).get(2),
                new java.sql.Date(new java.util.Date().getTime()));

        FixationOfPassage secondFixCard2 = new FixationOfPassage(
                ((List<TravelCard>) cards).get(1),
                ((List<PaymentLocation>) locations).get(0),
                new java.sql.Date(new java.util.Date().getTime()));

        return fixationOfPassageRepo.saveAll(Arrays.asList(firstFixCard1, firstFixCard2, secondFixCard1, secondFixCard2));
    }

    private Iterable<ReplenishmentType> setUpReplenishmentType(Iterable<Station> stations){
        //TODO: нужно ограничение не дающие записывать станцию при онлайн методе
        ReplenishmentType type1 = new ReplenishmentType(ReplenishmentMethod.ONLINE);
        ReplenishmentType type2 = new ReplenishmentType(((List<Station>) stations).get(1), ReplenishmentMethod.TERMINAL);
        return replenishmentTypeRepo.saveAll(Arrays.asList(type1, type2));
    }

    private Iterable<ReplenishmentCard> setUpReplenishmentCard(List<TravelCard> cards, List<ReplenishmentType> types){
        ReplenishmentCard firstRep = new ReplenishmentCard(cards.get(0), 1000L, types.get(0));
        ReplenishmentCard secondRep = new ReplenishmentCard(cards.get(0), 1500L, types.get(1));
        return replenishmentCardRepo.saveAll(Arrays.asList(firstRep, secondRep));
    }


    @Test
    public void testPassengers() {
        Passenger passenger1 = passengerRepo.findByName("Павел");
        Passenger passenger2 = passengerRepo.findBySoname("Полищук");

        assertEquals("Павел", passenger1.getName());
        assertEquals("Евгений", passenger2.getName());
    }

    @Test
    public void testGroundTransportationTest(){
        GroundTransportation transportation1 = groundTransportationRepo.getByTransport(Transport.BUS);
        GroundTransportation transportation2 = groundTransportationRepo.getByTransport(Transport.TRAM);

        assertEquals("30", transportation1.getRoute());
        assertEquals("64", transportation2.getRoute());
    }

    @Test
    public void testStation(){
        Station ladka = stationRepo.findByName("Ладожская");
        Station novochera = stationRepo.findByName("Новочеркасская");

        assertEquals(CheckerPayment.VALIDATOR, ladka.getPaymentLocation().getCheckerPayment());
        assertEquals(CheckerPayment.TURNSTILE, novochera.getPaymentLocation().getCheckerPayment());

    }

    @Test
    public void testFixationOfPassage(){
        List<TravelCard> cards = (List<TravelCard>) travelCardRepo.findAll();

        List<FixationOfPassage> card1Fixations = (List<FixationOfPassage>) fixationOfPassageRepo.findByTravelCard(cards.get(0));
        List<FixationOfPassage> card2Fixations = (List<FixationOfPassage>) fixationOfPassageRepo.findByTravelCard(cards.get(1));

        assertEquals(
                card1Fixations.get(0).getPaymentLocation().getCheckerPayment(),
                card1Fixations.get(1).getPaymentLocation().getCheckerPayment()
        );
        assertEquals(
                card2Fixations.get(0).getPaymentLocation().getCheckerPayment(),
                card2Fixations.get(1).getPaymentLocation().getCheckerPayment()
        );
    }

    @Test
    public void testReplenishmentType(){

        ReplenishmentType replenishmentOnline = replenishmentTypeRepo.findByMethod(ReplenishmentMethod.ONLINE);
        ReplenishmentType replenishmentTerminal = replenishmentTypeRepo.findByMethod(ReplenishmentMethod.TERMINAL);

        assertNull(replenishmentOnline.getStation());
        assertEquals("Новочеркасская", replenishmentTerminal.getStation().getName());

    }

    @Test
    public void testReplenishmentCard(){
        ReplenishmentCard replenishment1 = replenishmentCardRepo.findByAmount(1000);
        ReplenishmentCard replenishment2 = replenishmentCardRepo.findByAmount(1500);

        assertEquals(ReplenishmentMethod.ONLINE, replenishment1.getType().getMethod());
        assertEquals(ReplenishmentMethod.TERMINAL, replenishment2.getType().getMethod());
        assertEquals(replenishment1.getCard(), replenishment2.getCard());
    }



    @After
    public void clean(){
        passengerRepo.deleteAll();
        groundTransportationRepo.deleteAll();
        fixationOfPassageRepo.deleteAll();
        replenishmentCardRepo.deleteAll();
        travelCardRepo.deleteAll();
        replenishmentTypeRepo.deleteAll();
        stationRepo.deleteAll();
        paymentLocationRepo.deleteAll();
    }
}

