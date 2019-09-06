package learnTest;


import com.metroInformationSystem.Main;
import com.metroInformationSystem.domain.GroundTransportation;
import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.domain.PaymentLocation;
import com.metroInformationSystem.domain.Station;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.domain.enums.Transport;
import com.metroInformationSystem.repository.GroundTransportationRepo;
import com.metroInformationSystem.repository.PassengerRepo;
import com.metroInformationSystem.repository.PaymentLocationRepo;
import com.metroInformationSystem.repository.StationRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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


        // TODO: здесь наземному транспорту в качестве места оплаты передается турникет,
        // не забыть дописать запрещающий триггер
        GroundTransportation bus = new GroundTransportation(turnstile1, Transport.BUS, "30" );
        GroundTransportation tram = new GroundTransportation(validator1, Transport.TRAM, "64");

        groundTransportationRepo.save(bus);
        groundTransportationRepo.save(tram);

        Station ladka = new Station(validator2, "Ладожская");
        Station novochera = new Station(turnstile2, "Новочеркасская");

        stationRepo.save(ladka);
        stationRepo.save(novochera);

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

    @After
    public void clean(){
        passengerRepo.deleteAll();
        groundTransportationRepo.deleteAll();
        stationRepo.deleteAll();
        paymentLocationRepo.deleteAll();
    }

}


//@Before - выполняется перед каждым тестом
//@BeforeClass - выполняется один раз перед первым тестом

//@After - выполняется после каждого теста
//@AfterClass - выполняется один раз после последнего теста
