import org.junit.Test;
import java.sql.*;

import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testPassengersDatabaseManager {
    private final PassengerDatabaseManager passengerDb = new PassengerDatabaseManager();
    private final FlightDatabaseManager flightDb = new FlightDatabaseManager();
    private final AirlineDatabaseManager airlineDb = new AirlineDatabaseManager();
    private final Airlines airline = new Airlines("flyEmfbifoIVCISVC");
    private final AirportDatabaseManager airportDb = new AirportDatabaseManager();
    private final Airport airport1 = new Airport("groibdnbpon", "Minsk");
    private final Airport airport2 = new Airport("groibdnbp", "Moscow");
    private final PlaneDatabaseManager planeDb = new PlaneDatabaseManager();
    @Test
    public void testAddNewPassenger() throws SQLException{
        airlineDb.addNewAirline(airline);
        airportDb.addNewAirport(airport1);
        airportDb.addNewAirport(airport2);
        Plane plane = new Plane("boing", airlineDb.getAirlineId(airline.getName()), 100, "3000IG");
        planeDb.addNewPlane(plane);
        Flight flight = new Flight(airportDb.getAirportId(airport1.getName()), airportDb.getAirportId(airport2.getName()), airlineDb.getAirlineId(airline.getName()), planeDb.getPlaneId(plane.getPlaneNumber()),"Moscow-Minsk", Timestamp.valueOf("2023-12-10 00:00:00"),Timestamp.valueOf("2023-12-10 18:30:00"));
        flightDb.addNewFlight(flight);
        Passenger passenger = new Passenger("firstname", "lastname", "OUEVV3UIB8");
        passengerDb.addNewPassenger(passenger,flightDb.getFlightId(flight));
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM passengers WHERE passport_id = '" + passenger.getPassportId() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(passenger.getFirstName(), resultSet.getString("first_name"));
            assertEquals(passenger.getLastName(), resultSet.getString("last_name"));
            assertEquals(passenger.getId(), resultSet.getInt("id"));
            assertEquals(passenger.getPassportId(), resultSet.getString("passport_id"));
            assertEquals(passengerDb.getPassengersOnFlight(flightDb.getFlightId(flight)).size(), 1);
            assertTrue(passengerDb.deletePassengerFromFlight(flightDb.getFlightId(flight)));
            passengerDb.deletePassenger(passenger.getId());
            flightDb.deleteFlight(flightDb.getFlightId(flight));
            airlineDb.deleteAirline(airlineDb.getAirlineId(airline.getName()));
            airportDb.deleteAirport(airportDb.getAirportId(airline.getName()));
        }
    }
}
