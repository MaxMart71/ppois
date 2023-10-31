import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class testUser {
    private final DatabaseManager db = new DatabaseManager();
    private final User user = new User("max", "usyuY6&yf", "max@gmail.com");

    @Test
    public void testRentCar(){
        User user = new User("copodbn", "9^tuttJ", "coeodbg@gmail.com");
        Car car = new Car("7329HJ-1", "audi", 10, "electrocar");
        db.addNewUser(user);
        db.addNewCar(car);
        user.rentCar(car.getCarId());
        assertEquals(user.getPersonId(), db.getUserIdByCarId(car.getCarId()));
        assertEquals(car.getCarId(), db.getCarIdByWasRentedDate(db.getWasRentedDateByCarId(car.getCarId())));
        db.deleteAfterTest();
    }

    @Test
    public void testReturnCar() {
        User user = new User("fio", "flag", "edoig@gamil.com");
        Car car = new Car("1234RT-6", "bmw", 13, "sedan");
        db.addNewCar(car);
        db.addNewUser(user);
        db.rentCar(car.getCarId(), user.getPersonId());
        user.returnCar(car.getCarId());
        assertEquals(0, db.getUserIdByCarId(car.getCarId()));
        assertNotNull(db.getWasReturnDateByCarId(car.getCarId()));
        db.deleteAfterTest();
    }
    @Test
    public void testGetALlTypesOfCars(){
        assertTrue(User.getALlTypesOfCars());
    }
    @Test
    public void getAllAvailableCars(){
        assertTrue(user.getAllAvailableCars());
    }

}
