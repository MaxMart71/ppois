import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testArlineDatabaseManager {
    private final AirlineDatabaseManager airlineDb = new AirlineDatabaseManager();
    private final Airlines airline = new Airlines("flyEmfbifoIVCISVC");
    @Test
    public void testAddNewAirline() throws SQLException{
        airlineDb.addNewAirline(airline);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM airlines WHERE name = '" + airline.getName() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(airline.getName(), resultSet.getString("name"));
            assertEquals(airlineDb.getAirlineId(airline.getName()), resultSet.getInt("id"));
            airlineDb.deleteAirline(airlineDb.getAirlineId(airline.getName()));
        }
    }
}
