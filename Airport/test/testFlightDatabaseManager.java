import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testFlightDatabaseManager {
    private final FlightDatabaseManager flightDb = new FlightDatabaseManager();
    private final AirlineDatabaseManager airlineDb = new AirlineDatabaseManager();
    private final Airlines airline = new Airlines("flyEmfbifoIVCISVC");
    private final AirportDatabaseManager airportDb = new AirportDatabaseManager();
    private final Airport airport1 = new Airport("groibdnbpon", "Minsk");
    private final Airport airport2 = new Airport("groibdnbp", "Moscow");
    private final PlaneDatabaseManager planeDb = new PlaneDatabaseManager();
    @Test
    public void testAddNewFlight() throws SQLException{
        airlineDb.addNewAirline(airline);
        airportDb.addNewAirport(airport1);
        airportDb.addNewAirport(airport2);
        Plane plane = new Plane("boing", airlineDb.getAirlineId(airline.getName()), 100, "3000IG");
        planeDb.addNewPlane(plane);
        Flight flight = new Flight(airportDb.getAirportId(airport1.getName()), airportDb.getAirportId(airport2.getName()), airlineDb.getAirlineId(airline.getName()), planeDb.getPlaneId(plane.getPlaneNumber()),"Moscow-Minsk", Timestamp.valueOf("2023-12-10 00:00:00"),Timestamp.valueOf("2023-12-10 18:30:00"));
        flightDb.addNewFlight(flight);
        int temp = flightDb.getAvailablePlaces(flightDb.getFlightId(flight));
        flightDb.changingPlacesAmount(flightDb.getFlightId(flight));
        int result = flightDb.getAvailablePlaces(flightDb.getFlightId(flight));
        assertEquals(temp-1, result);
        assertTrue(flightDb.isPlacesAvailable(flightDb.getFlightId(flight)));
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM flights WHERE flight_name = '" + flight.getFlightName() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(flight.getPlaneId(), resultSet.getInt("plane_id"));
            assertEquals(flight.getDepartureAirportId(), resultSet.getInt("departure_airport_id"));
            assertEquals(flight.getArrivalAirportId(), resultSet.getInt("arrival_airport_id"));
            assertEquals(flight.getFlightName(), resultSet.getString("flight_name"));
            assertEquals(flight.getArrivalTime(), resultSet.getTimestamp("arrival_time"));
            assertEquals(flightDb.getAllFlights().size(), 1);
            flightDb.deleteFlight(flightDb.getFlightId(flight));
            airlineDb.deleteAirline(airlineDb.getAirlineId(airline.getName()));
            airportDb.deleteAirport(airportDb.getAirportId(airline.getName()));
        }
    }
}
