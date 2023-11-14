import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class testUser {
    private final DatabaseManager db = new DatabaseManager();
    private final User user = new User("max", "usyuY6&yf", "maxim@gmail.com");

    @Test
    public void testRentCar(){
        User user = new User("pop", "9^tuttJ", "ear@gmail.com");
        Car car = new Car("3047GU-1", "audi", 10, "electrocar");
        db.addNewUser(user);
        db.addNewCar(car);
        db.rentCar(car.getCarId(), db.getPersonId(user));
        assertNotNull(db.getPersonId(user));
        assertNotNull(car.getCarId());
        assertNotNull(db.getWasRentedDateByCarId(car.getCarId()));
        db.deleteAfterTest();
        db.deleteUser(db.getPersonId(user));
        db.deleteCar(car.getCarId());
    }

    @Test
    public void testReturnCar() {
        User user = new User("oo", "9^tuttJ", "e@gmail.com");
        Car car = new Car("3046GP-1", "audi", 10, "electrocar");
        db.addNewCar(car);
        db.addNewUser(user);
        db.rentCar(car.getCarId(), db.getPersonId(user));
        user.returnCar(car.getCarId());
        assertNotNull(db.getWasReturnDateByCarId(car.getCarId()));
        db.deleteAfterTest();
        db.deleteCar(car.getCarId());
        db.deleteUser(db.getPersonId(user));
    }

}
