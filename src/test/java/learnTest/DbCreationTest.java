package learnTest;


import com.metroInformationSystem.Main;
import com.metroInformationSystem.domain.GroundTransportation;
import com.metroInformationSystem.domain.Passenger;
import com.metroInformationSystem.domain.PaymentLocation;
import com.metroInformationSystem.domain.enums.CheckerPayment;
import com.metroInformationSystem.domain.enums.Transport;
import com.metroInformationSystem.repository.GroundTransportationRepo;
import com.metroInformationSystem.repository.PassengerRepo;
import com.metroInformationSystem.repository.PaymentLocationRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DbCreationTest {

    @Autowired
    private PassengerRepo passengerRepo;
    @Autowired
    private PaymentLocationRepo paymentLocationRepo;
    @Autowired
    private GroundTransportationRepo groundTransportationRepo;


    @Before
    @Rollback(false)
    public void setUpDB() throws Exception {
        Passenger passenger1 = new Passenger("Тимофеев", "Павел", "Анатольевич");
        Passenger passenger2 = new Passenger("Полищук", "Евгений", "Александрович");

        passengerRepo.save(passenger1);
        passengerRepo.save(passenger2);

        PaymentLocation paymentLocation1 = new PaymentLocation(CheckerPayment.TURNSTILE);
        PaymentLocation paymentLocation2 = new PaymentLocation(CheckerPayment.VALIDATOR);

        paymentLocationRepo.save(paymentLocation1);
        paymentLocationRepo.save(paymentLocation2);

        // TODO: здесь наземному транспорту в качестве места оплаты передается турникет,
        // не забыть дописать запрещающий триггер
        GroundTransportation groundTransportation1 =
                new GroundTransportation(paymentLocation1, Transport.BUS, "30" );
        GroundTransportation groundTransportation2 =
                new GroundTransportation(paymentLocation2, Transport.TRAM, "64");

        groundTransportationRepo.save(groundTransportation1);

        groundTransportationRepo.save(groundTransportation2);

    }


    @Test
    public void testPassengers() {
        Passenger passenger1 = passengerRepo.findByName("Павел");
        Passenger passenger2 = passengerRepo.findBySoname("Полищук");

        Assert.assertEquals("Павел", passenger1.getName());
        Assert.assertEquals("Евгений", passenger2.getName());
    }

    @Test
    public void testGroundTransportationTest(){
        GroundTransportation transportation1 = groundTransportationRepo.getByTransport(Transport.BUS);
        GroundTransportation transportation2 = groundTransportationRepo.getByTransport(Transport.TRAM);

        Assert.assertEquals("30", transportation1.getRoute());
        Assert.assertEquals("64", transportation2.getRoute());
    }

    @After
    public void clean(){
        passengerRepo.deleteAll();
        groundTransportationRepo.deleteAll();
        paymentLocationRepo.deleteAll();
    }

}


//@Before - выполняется перед каждым тестом
//@BeforeClass - выполняется один раз перед первым тестом

//@After - выполняется после каждого теста
//@AfterClass - выполняется один раз после последнего теста
