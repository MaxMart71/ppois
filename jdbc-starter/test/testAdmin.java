import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;



public class testAdmin {
    private final DatabaseManager db = new DatabaseManager();
    private final User user = new User("test1", "qW3*yu", "max@gmail.com");
    private final Car car = new Car("7230PO-1", "audi", 15, "electrocar");

    private final Admin admin = new Admin("admin", "admin");

    private final Admin admin_with_email = new Admin("admin", "admin", "admin_email@gmail.com");

    @Test
    public void testAddNewUser() throws SQLException, NoSuchAlgorithmException {
        admin.addNewUser(user);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM person WHERE email = '" + user.getEmail() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(user.getUsername(), resultSet.getString("username"));
            assertEquals(Hash.getHash(user.getPassword()), resultSet.getString("password"));
            assertEquals(user.getEmail(), resultSet.getString("email"));
            int user_id = resultSet.getInt("id");
            db.deleteUser(user_id);
        }
    }
    @Test
    public void testDeleteUser(){
        admin.addNewUser(user);
        assertNotEquals(-1, user.getPersonId());
        admin.deleteUser(user.getPersonId());
        int deletedUserId = db.getUserIdByUsername("test1");
        assertEquals(-1, deletedUserId);
    }
    @Test
    public void testAddNewCar() throws SQLException {
        admin.addNewCar(car);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM car WHERE car_id = '" + car.getCarId() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(car.getCarId(), resultSet.getString("car_id"));
            assertEquals(car.getCarName(), resultSet.getString("carname"));
            assertEquals(car.getPricePerHour(), resultSet.getInt("price_per_hour"));
            assertEquals(car.getType(), resultSet.getString("vehicle_type"));
            String car_id = resultSet.getString("car_id");
            admin.deleteCar(car_id);
        }
    }
    @Test
    public void testDeleteCar(){
        admin.addNewCar(car);
        assertEquals(15, db.getPricePerHourByCarId(car.getCarId()));
        admin.deleteCar(car.getCarId());
        assertEquals(0, db.getPricePerHourByCarId(car.getCarId()));
    }

}
