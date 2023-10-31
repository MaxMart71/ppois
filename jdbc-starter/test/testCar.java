import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
public class testCar {
    private final String car_id = "3563UI-6";
    private final String car_name = "audi";
    private final int price_per_hour = 10;
    private final String type = "electrocar";
    private User user = new User("", "", "");
    private Car car = new Car("","", 0, "");

    @Test
    public void testSetCarId(){
        car.setCarId(car_id);
        assertEquals(car.getCarId(), car_id);
    }
    @Test
    public void testSetCarName(){
        car.setCarName(car_name);
        assertEquals(car.getCarName(), car_name);
    }
    @Test
    public void testSetType(){
        car.setType(type);
        assertEquals(car.getType(), type);
    }
    @Test
    public void testSetPricePerHour(){
        car.setPricePerHour(price_per_hour);
        assertEquals(car.getPricePerHour(), price_per_hour);
    }
    @Test
    public void testSetUser(){
        car.setUser(user);
        assertEquals(car.getUser(), user);
    }

}
