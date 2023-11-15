//import org.junit.Test;
//
//import java.sql.*;
//
//import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//
//public class testDatabaseManager {
//    private final Admin admin = new Admin("name", "surname", "OGEOB9667VID", 1);
//    private final Airport airport1 = new Airport("groibdnbpon", "Minsk");
//    private final Airport airport2 = new Airport("aoibfff", "Moscow");
//    private final Airlines airline = new Airlines("flyEmfbifoIVCISVC");
//    private final Flight flight = new Flight(1, 2, 3,1,"Moscow-Minsk", Timestamp.valueOf("2023-12-10 00:00:00"),Timestamp.valueOf("2023-12-10 18:30:00"));
//    private final Employee employee = new Employee("firstName", "lastName","DBOE23BUIDIV32", 10000, "pilot");
//    private final Passenger passenger = new Passenger("firstName", "lastName","DBOE2F5OBE");
//
//    @Test
//    public void testAddNewEmployee() throws SQLException{
//        db.addNewEmployee(employee);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM employees WHERE passport_id = '" + employee.getPassportId() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(employee.getFirstName(), resultSet.getString("first_name"));
//            assertEquals(employee.getLastName(), resultSet.getString("last_name"));
//            assertEquals(employee.getId(), resultSet.getInt("id"));
//            assertEquals(employee.getWorkingPosition(), resultSet.getString("working_position"));
//            assertEquals(employee.getPassportId(), resultSet.getString("passport_id"));
//            db.deleteEmployee(employee.getId());
//        }
//    }
//
//    @Test
//    public void testAddNewAirport() throws SQLException{
//        db.addNewAirport(airport1);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM airport WHERE name = '" + airport1.getName() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(airport1.getName(), resultSet.getString("name"));
//            assertEquals(airport1.getLocation(), resultSet.getString("location"));
//            db.deleteAirport(db.getAirportId(airport1.getName()));
//        }
//    }
//    @Test
//    public void testAddNewAirline() throws SQLException{
//        db.addNewAirline(airline);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM airlines WHERE name = '" + airline.getName() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(airline.getName(), resultSet.getString("name"));
//            assertEquals(db.getAirlineId(airline.getName()), resultSet.getInt("id"));
//            db.deleteAirline(db.getAirlineId(airline.getName()));
//        }
//    }
//    @Test
//    public void testAddNewPlane() throws SQLException{
//        db.addNewPlane(plane);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM planes WHERE model = '" + plane.getModel() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(plane.getModel(), resultSet.getString("model"));
//            assertEquals(plane.getAmountOfPlaces(), resultSet.getInt("amount_of_available_places"));
//            db.deletePlane(db.getPlaneId(plane));
//        }
//    }
//    @Test
//    public void testAddNewFlight() throws SQLException{
//        db.addNewFlight(flight);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM flights WHERE flight_name = '" + flight.getFlightName() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(flight.getPlaneId(), resultSet.getInt("plane_id"));
//            assertEquals(flight.getDepartureAirportId(), resultSet.getInt("departure_airport_id"));
//            assertEquals(flight.getArrivalAirportId(), resultSet.getInt("arrival_airport_id"));
//            assertEquals(flight.getFlightName(), resultSet.getString("flight_name"));
//            assertEquals(flight.getArrivalTime(), resultSet.getTimestamp("arrival_time"));
//            db.deleteFlight(flight);
//        }
//    }
//    @Test
//    public void testAddNewPassenger() throws SQLException{
//        db.addNewPassenger(passenger);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM passengers WHERE passport_id = '" + passenger.getPassportId() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(passenger.getFirstName(), resultSet.getString("first_name"));
//            assertEquals(passenger.getLastName(), resultSet.getString("last_name"));
//            assertEquals(passenger.getId(), resultSet.getInt("id"));
//            assertEquals(passenger.getPassportId(), resultSet.getString("passport_id"));
//            db.deleteEmployee(employee.getId());
//        }
//    }
//    @Test
//    public void testAdd() throws SQLException{
//        db.addNewEmployee(employee);
//        try (Connection con = ConnectionManager.open();
//             Statement st = con.createStatement();
//             ResultSet resultSet = st.executeQuery("SELECT * FROM employees WHERE passport_id = '" + employee.getPassportId() + "'")) {
//            assertTrue(resultSet.next());
//            assertEquals(employee.getFirstName(), resultSet.getString("first_name"));
//            assertEquals(employee.getLastName(), resultSet.getString("last_name"));
//            assertEquals(employee.getId(), resultSet.getInt("id"));
//            assertEquals(employee.getWorkingPosition(), resultSet.getString("working_position"));
//            assertEquals(employee.getPassportId(), resultSet.getString("passport_id"));
//            db.deleteEmployee(employee.getId());
//        }
//    }
//    @Test
//    public void testGetIdByAdmin(){
//        assertEquals(admin.getId(), 1);
//    }
//}
