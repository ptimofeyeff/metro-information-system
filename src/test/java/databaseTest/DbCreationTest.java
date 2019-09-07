package databaseTest;

import com.metroInformationSystem.Main;
import com.metroInformationSystem.domain.*;
import com.metroInformationSystem.domain.enums.CheckerPayment;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


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


    @Before
    @Rollback(false)
    public void setUpDB() {
        Passenger pasha = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger asdas = new Passenger("Полищук", "Евгений", "Александрович");
        passengerRepo.save(pasha);
        passengerRepo.save(asdas);

        PaymentLocation turnstile1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation validator1 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation validator2 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation turnstile2 = new PaymentLocation(CheckerPayment.TURNSTILE);
        paymentLocationRepo.saveAll(Arrays.asList(validator1, validator2, turnstile1, turnstile2));


        // TODO: наземному транспорту подсовывается турникет для метро
        GroundTransportation bus = new GroundTransportation(turnstile1, Transport.BUS, "30" );
        GroundTransportation tram = new GroundTransportation(validator1, Transport.TRAM, "64");
        groundTransportationRepo.save(bus);
        groundTransportationRepo.save(tram);

        // TODO: метро подсовывается валидатор для наземного транспорта
        Station ladka = new Station(validator2, "Ладожская");
        Station novochera = new Station(turnstile2, "Новочеркасская");
        stationRepo.save(ladka);
        stationRepo.save(novochera);

        TravelCard card1 = new TravelCard();
        TravelCard card2 = new TravelCard();
        travelCardRepo.saveAll(Arrays.asList(card1, card2));

        FixationOfPassage firstFixCard1 =
                new FixationOfPassage(card1, turnstile1, new java.sql.Date(new java.util.Date().getTime()));
        FixationOfPassage firstFixCard2 =
                new FixationOfPassage(card2, validator1, new java.sql.Date(new java.util.Date().getTime()));
        FixationOfPassage secondFixCard1 =
                new FixationOfPassage(card1, turnstile1, new java.sql.Date(new java.util.Date().getTime()));
        FixationOfPassage secondFixCard2 =
                new FixationOfPassage(card2, validator1, new java.sql.Date(new java.util.Date().getTime()));

        fixationOfPassageRepo.saveAll(Arrays.asList(firstFixCard1, firstFixCard2, secondFixCard1, secondFixCard2 ));
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
        ArrayList<TravelCard> cards = (ArrayList<TravelCard>) travelCardRepo.findAll();

        ArrayList<FixationOfPassage> card1Fixations = (ArrayList<FixationOfPassage>) fixationOfPassageRepo.findByTravelCard(cards.get(0));
        ArrayList<FixationOfPassage> card2Fixations = (ArrayList<FixationOfPassage>) fixationOfPassageRepo.findByTravelCard(cards.get(1));

        assertEquals(
                card1Fixations.get(0).getPaymentLocation().getCheckerPayment(),
                card1Fixations.get(1).getPaymentLocation().getCheckerPayment()
        );
        assertEquals(
                card2Fixations.get(0).getPaymentLocation().getCheckerPayment(),
                card2Fixations.get(1).getPaymentLocation().getCheckerPayment()
        );
    }


    @After
    public void clean(){
        passengerRepo.deleteAll();
        groundTransportationRepo.deleteAll();
        stationRepo.deleteAll();
        fixationOfPassageRepo.deleteAll();
        travelCardRepo.deleteAll();
        paymentLocationRepo.deleteAll();
    }

}

