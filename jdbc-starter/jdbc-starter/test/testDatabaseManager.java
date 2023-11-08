import org.junit.After;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static org.junit.Assert.*;
public class testDatabaseManager {
    private final DatabaseManager db = new DatabaseManager();
    private final Admin admin = new Admin("admin", "admin");

    private final User user = new User("eogbdfber", "qW3*yuIh", "esppanol@gmail.com");
    private final Car car = new Car("1247OP-1", "audi", 15, "electrocar");
    private final int HOUR_IN_MILLISECONDS = 3600000;


    @Test
    public void testGetPasswordByUsername() throws NoSuchAlgorithmException {
        db.addNewUser(user);
        assertEquals(Hash.getHash(user.getPassword()), db.getPasswordByUsername(user.getUsername()));
        db.deleteUser(user.getPersonId());
    }
    @Test
    public void testGetUsernameById(){
        db.addNewUser(user);
        assertEquals(user.getUsername(), db.getUsernameById(user.getPersonId()));
        db.deleteUser(user.getPersonId());
    }
    @Test
    public void testGetPricePerHourByCarId(){
        db.addNewCar(car);
        assertEquals(car.getPricePerHour(), db.getPricePerHourByCarId(car.getCarId()));
        db.deleteCar(car.getCarId());
    }

    @Test
    public void testIsUsernameTaken(){
        User user = new User("cow", "9^trte", "djo@gmail.com");
        db.addNewUser(user);
        String not_taken_username = "123";
        assertTrue(db.isUsernameTaken(user.getUsername()));
        assertFalse(db.isUsernameTaken(not_taken_username) );
        db.deleteUser(user.getPersonId());
    }
    @Test
    public void testGetFinalPrice() throws InterruptedException {
        db.addNewUser(user);
        db.addNewCar(car);
        db.rentCar(car.getCarId(), user.getPersonId());
        long rentedDate = db.getWasRentedDateByCarId(car.getCarId());
        Thread.sleep(2000);
        db.returnCar(car.getCarId());
        long returnedDate = db.getWasReturnDateByCarId(car.getCarId());
        double realResult = db.getFinalPrice(car.getCarId());
        double result = (double)(returnedDate-rentedDate) * db.getPricePerHourByCarId(car.getCarId()) / HOUR_IN_MILLISECONDS;
        assertEquals(realResult, result, 0.001);
    }

}
