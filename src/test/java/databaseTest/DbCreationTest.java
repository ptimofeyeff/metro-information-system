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
import org.postgresql.util.PGInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    @Autowired
    private TariffRepo tariffRepo;
    @Autowired
    private ExemptRepo exemptRepo;
    @Autowired
    private PassengerCardRepo passengerCardRepo;
    @Autowired
    private ExemptionRepo exemptionRepo;


    @Before
    @Rollback(false)
    public void setUpDB() {
        Iterable<PaymentLocation> locations = setUpPaymentLocation();
        Iterable<Passenger> passengers = setUpPassengers();
        Iterable<GroundTransportation> groundTransportation = setUpGroundTransportation((List<PaymentLocation>) locations);
        Iterable<Station> stations = setUpStation((List<PaymentLocation>) locations);
        Iterable<TravelCard> cards = setUpTravelCard();
        Iterable<FixationOfPassage> fixations = setUpFixationOfPassage((List<TravelCard>) cards, (List<PaymentLocation>) locations);
        Iterable<ReplenishmentType> replenishmentTypes = setUpReplenishmentType((List<Station>) stations);
        Iterable<ReplenishmentCard> replenishmentCards =
                setUpReplenishmentCard((List<TravelCard>) cards, (List<ReplenishmentType>) replenishmentTypes);
        Iterable<Tariff> tariffs = setUpTariff();
        Iterable<PassengerCard> passengerCards = setUpPassengerCard((List<Passenger>) passengers,
                (List<TravelCard>) cards, (List<Tariff>) tariffs);
        setUpExemptAndExemption((List<Passenger>) passengers);
    }

    private Iterable<Passenger> setUpPassengers() {
        Passenger pasha = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger asdas = new Passenger("Полищук", "Евгений", "Александрович");
        Passenger vanek = new Passenger("Иванов", "Иван", "Иваныч");

        return passengerRepo.saveAll(Arrays.asList(pasha, asdas, vanek));
    }

    private Iterable<PaymentLocation> setUpPaymentLocation() {

        PaymentLocation turnstile1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation validator1 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation validator2 = new PaymentLocation(CheckerPayment.VALIDATOR);
        PaymentLocation turnstile2 = new PaymentLocation(CheckerPayment.TURNSTILE);

        return paymentLocationRepo.saveAll(Arrays.asList(validator1, validator2, turnstile1, turnstile2));
    }

    private Iterable<GroundTransportation> setUpGroundTransportation(List<PaymentLocation> locations) {

        // TODO: нужно ограничение не дающие записывать в качестве места оплаты турникет метро
        GroundTransportation bus = new GroundTransportation(locations.get(2), Transport.BUS, "30");
        GroundTransportation tram = new GroundTransportation(locations.get(0), Transport.TRAM, "64");

        return groundTransportationRepo.saveAll(Arrays.asList(bus, tram));
    }

    private Iterable<Station> setUpStation(List<PaymentLocation> locations) {

        // TODO: нужно ограничение не дающие записывать в качестве места оплаты валидатор наземного транспорта
        Station ladka = new Station(locations.get(1), "Ладожская");
        Station novochera = new Station(locations.get(3), "Новочеркасская");

        return stationRepo.saveAll(Arrays.asList(ladka, novochera));
    }

    private Iterable<TravelCard> setUpTravelCard() {

        TravelCard card1 = new TravelCard();
        TravelCard card2 = new TravelCard();

        return travelCardRepo.saveAll(Arrays.asList(card1, card2));
    }

    private Iterable<FixationOfPassage> setUpFixationOfPassage(List<TravelCard> cards, List<PaymentLocation> locations) {

        FixationOfPassage firstFixCard1 = new FixationOfPassage(cards.get(0), locations.get(2));
        FixationOfPassage firstFixCard2 = new FixationOfPassage(cards.get(1), locations.get(0));
        FixationOfPassage secondFixCard1 = new FixationOfPassage(cards.get(0), locations.get(2));
        FixationOfPassage secondFixCard2 = new FixationOfPassage(cards.get(1), locations.get(0));

        return fixationOfPassageRepo.saveAll(Arrays.asList(firstFixCard1, firstFixCard2, secondFixCard1, secondFixCard2));
    }

    private Iterable<ReplenishmentType> setUpReplenishmentType(List<Station> stations) {
        //TODO: нужно ограничение не дающие записывать станцию при онлайн методе
        ReplenishmentType type1 = new ReplenishmentType(ReplenishmentMethod.ONLINE);
        ReplenishmentType type2 = new ReplenishmentType(stations.get(1), ReplenishmentMethod.TERMINAL);
        return replenishmentTypeRepo.saveAll(Arrays.asList(type1, type2));
    }

    private Iterable<ReplenishmentCard> setUpReplenishmentCard(List<TravelCard> cards, List<ReplenishmentType> types) {
        ReplenishmentCard firstRep = new ReplenishmentCard(cards.get(0), 1000L, types.get(0));
        ReplenishmentCard secondRep = new ReplenishmentCard(cards.get(0), 1500L, types.get(1));
        return replenishmentCardRepo.saveAll(Arrays.asList(firstRep, secondRep));
    }

    private Iterable<Tariff> setUpTariff() {
        Tariff tariff1 = new Tariff("Единый студенческий проездной на месяц",
                1035, new PGInterval(0, 1, 0, 0, 0, 0), 100, Integer.MAX_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, new Time(0));

        Tariff tariff2 = new Tariff("ПБ Автобус",
                1555, new PGInterval(0, 1, 0, 0, 0, 0), 0, Integer.MAX_VALUE, 0, 0, new Time(0));

        return tariffRepo.saveAll(Arrays.asList(tariff1, tariff2));
    }

    private void setUpExemptAndExemption(List<Passenger> passengers) {
        Exempt exempt1 = new Exempt(passengers.get(0),
                null, LocalDateTime.now(), LocalDateTime.now().plusYears(255));

        Exempt exempt2 = new Exempt(passengers.get(1),
                null, LocalDateTime.now(), LocalDateTime.now().plusYears(4));

        Exempt exempt3 = new Exempt(passengers.get(2),
                null, LocalDateTime.now(), LocalDateTime.now().plusYears(4));


        Exemption retiree = new Exemption("Пенсионер", Arrays.asList(exempt1, exempt3), true);
        Exemption veteran = new Exemption("Ветеран ВОВ", Collections.singletonList(exempt1), true);


        Exemption student = new Exemption("Студент очной формы обучения", Collections.singletonList(exempt2), false);

        exempt1.setExemptions(Arrays.asList(retiree, veteran));
        exempt2.setExemptions(Collections.singletonList(student));
        exempt3.setExemptions(Collections.singletonList(retiree));

        exemptionRepo.saveAll(Arrays.asList(retiree, veteran, student));
        exemptRepo.saveAll(Arrays.asList(exempt1, exempt2, exempt3));


    }

    private Iterable<PassengerCard> setUpPassengerCard(List<Passenger> passengers,
                                                       List<TravelCard> cards,
                                                       List<Tariff> tariffs) {

        LocalDateTime recharge = LocalDateTime.now();
        LocalDateTime expiration = recharge.plusMonths(1);
        Tariff tariff1 = tariffs.get(0);
        Tariff tariff2 = tariffs.get(1);


        PassengerCard passengerCard1 = new PassengerCard(passengers.get(0), cards.get(0),
                tariff1, recharge, expiration);

        PassengerCard passengerCard2 = new PassengerCard(passengers.get(0), cards.get(1),
                tariff2, recharge, expiration);

        return passengerCardRepo.saveAll(Arrays.asList(passengerCard1, passengerCard2));
    }

    @Test
    public void testPassengersCard() {
        Passenger passenger = passengerRepo.findByName("Павел");
        TravelCard travelCard1 = travelCardRepo.findById(5L).get();
        TravelCard travelCard2 = travelCardRepo.findById(6L).get();

        PassengerCard passengerCard1 = passengerCardRepo.findByPassengerAndTravelCard(passenger, travelCard1);
        PassengerCard passengerCard2 = passengerCardRepo.findByPassengerAndTravelCard(passenger, travelCard2);

        assertEquals(passengerCard1.getTariff().getCost(), 1035);
        assertEquals(passengerCard2.getTariff().getCost(), 1555);
    }

    @Test
    public void testPassengers() {
        Passenger passenger1 = passengerRepo.findByName("Павел");
        Passenger passenger2 = passengerRepo.findBySoname("Полищук");

        assertEquals("Павел", passenger1.getName());
        assertEquals("Евгений", passenger2.getName());
    }

    @Test
    public void testGroundTransportationTest() {
        GroundTransportation transportation1 = groundTransportationRepo.getByTransport(Transport.BUS);
        GroundTransportation transportation2 = groundTransportationRepo.getByTransport(Transport.TRAM);

        assertEquals("30", transportation1.getRoute());
        assertEquals("64", transportation2.getRoute());
    }

    @Test
    public void testStation() {
        Station ladka = stationRepo.findByName("Ладожская");
        Station novochera = stationRepo.findByName("Новочеркасская");

        assertEquals(CheckerPayment.VALIDATOR, ladka.getPaymentLocation().getCheckerPayment());
        assertEquals(CheckerPayment.TURNSTILE, novochera.getPaymentLocation().getCheckerPayment());

    }

    @Test
    public void testFixationOfPassage() {
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
    public void testReplenishmentType() {

        ReplenishmentType replenishmentOnline = replenishmentTypeRepo.findByMethod(ReplenishmentMethod.ONLINE);
        ReplenishmentType replenishmentTerminal = replenishmentTypeRepo.findByMethod(ReplenishmentMethod.TERMINAL);

        assertNull(replenishmentOnline.getStation());
        assertEquals("Новочеркасская", replenishmentTerminal.getStation().getName());

    }

    @Test
    public void testReplenishmentCard() {
        ReplenishmentCard replenishment1 = replenishmentCardRepo.findByAmount(1000);
        ReplenishmentCard replenishment2 = replenishmentCardRepo.findByAmount(1500);

        assertEquals(ReplenishmentMethod.ONLINE, replenishment1.getType().getMethod());
        assertEquals(ReplenishmentMethod.TERMINAL, replenishment2.getType().getMethod());
        assertEquals(replenishment1.getCard(), replenishment2.getCard());
    }

    @Test
    public void testTariff() {
        Tariff tariff1 = tariffRepo.findByName("Единый студенческий проездной на месяц");
        Tariff tariff2 = tariffRepo.findByName("ПБ Автобус");

        assertEquals(1035, tariff1.getCost());
        assertEquals(1, tariff2.getValidity().getMonths());
    }

    @Test
    public void testExemptAndExemption() {
        Passenger passenger = passengerRepo.findByName("Павел");

        Exempt exempt = exemptRepo.findByPassenger(passenger);
        Exemption exemption = exemptionRepo.findByName("Студент очной формы обучения");

        Collection<Exempt> exempts = exemption.getExempts();
        for (Exempt exempt1 : exempts) {
            Passenger passenger1 = exempt1.getPassenger();
            assertEquals("Евгений", passenger1.getName());
        }
        Collection<Exemption> exemptions = exempt.getExemptions();
        assertEquals(2, exemptions.size());
    }


    @After
    public void clean() {
        passengerCardRepo.deleteAll();
        exemptRepo.deleteAll();
        exemptionRepo.deleteAll();
        passengerRepo.deleteAll();
        groundTransportationRepo.deleteAll();
        fixationOfPassageRepo.deleteAll();
        replenishmentCardRepo.deleteAll();
        travelCardRepo.deleteAll();
        replenishmentTypeRepo.deleteAll();
        stationRepo.deleteAll();
        paymentLocationRepo.deleteAll();
        tariffRepo.deleteAll();

    }
}

