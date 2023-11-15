import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testPlaneDatabaseManager {
    private final PlaneDatabaseManager planeDb = new PlaneDatabaseManager();
    private final AirlineDatabaseManager airlineDb = new AirlineDatabaseManager();
    private final Airlines airline = new Airlines("flyEmfbifoIVCISVC");
    @Test
    public void testAddNewPlane() throws SQLException{
        airlineDb.addNewAirline(airline);
        Plane plane = new Plane("boing", airlineDb.getAirlineId(airline.getName()), 100, "3000IG");
        planeDb.addNewPlane(plane);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM planes WHERE model = '" + plane.getModel() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(plane.getModel(), resultSet.getString("model"));
            assertEquals(plane.getAmountOfPlaces(), resultSet.getInt("amount_of_places"));
            planeDb.deletePlane(planeDb.getPlaneId(plane.getPlaneNumber()));
            airlineDb.deleteAirline(airlineDb.getAirlineId(airline.getName()));
        }
    }
}
