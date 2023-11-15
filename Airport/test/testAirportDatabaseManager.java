import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testAirportDatabaseManager {
    private final AirportDatabaseManager airportDb = new AirportDatabaseManager();
    private final Airport airport = new Airport("groibdnbpon", "Minsk");
    @Test
    public void testAddNewAirport() throws SQLException{
        airportDb.addNewAirport(airport);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM airport WHERE name = '" + airport.getName() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(airport.getName(), resultSet.getString("name"));
            assertEquals(airport.getLocation(), resultSet.getString("location"));
            airportDb.deleteAirport(airportDb.getAirportId(airport.getName()));
        }
    }
}
