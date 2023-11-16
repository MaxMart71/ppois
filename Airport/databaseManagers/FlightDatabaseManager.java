import java.sql.*;
import java.util.*;
public class FlightDatabaseManager {
    private final PlaneDatabaseManager planeDb = new PlaneDatabaseManager();
    /**
     * Adding flight into database
     * @param flight flight that we want to add*/
    public void addNewFlight(Flight flight){
        String query = "INSERT INTO flights (plane_id, departure_airport_id, arrival_airport_id,airlines_id, departure_time, arrival_time,flight_name, available_places) VALUES (?,?,?,?,?,?,?,?)";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flight.getPlaneId());
            st.setInt(2, flight.getDepartureAirportId());
            st.setInt(3, flight.getArrivalAirportId());
            st.setInt(4, flight.getAirlinesId());
            st.setTimestamp(5, flight.getDepartureTime());
            st.setTimestamp(6, flight.getArrivalTime());
            st.setString(7, flight.getFlightName());
            st.setInt(8, planeDb.getAmountOfPlacesByPlaneId(flight.getPlaneId()));

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deleting flight from database
     * @param flightId if of theflight that we want to delete*/
    public void deleteFlight(int flightId){
        String query = "DELETE FROM flights WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting all flights from database
     * @return flights list that contains all flights*/
    public List<String> getAllFlights(){
        ArrayList<String> flights = new ArrayList<>();
        String query = "SELECT flight_name FROM flights";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    flights.add(resultSet.getString("flight_name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
    public int getFlightId(Flight flight){
        String query = "SELECT id FROM flights WHERE (plane_id, flight_name, arrival_time) = (?,?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flight.getPlaneId());
            st.setString(2, flight.getFlightName());
            st.setTimestamp(3,flight.getArrivalTime() );

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public int getAvailablePlaces(int flightId){
        String query = "SELECT available_places FROM flights WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("available_places");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    /**
     * Changing amount of available places in the plane
     * @param flightId id of the plane
     * */
    public void changingPlacesAmount(int flightId){
        String query = "UPDATE flights SET available_places = available_places - 1 WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Checking if there is 1 and more available places in the plane
     * @param flightId id of the plane
     * @return true/false if there is available places/not
     * */
    public boolean isPlacesAvailable(int flightId){
        String query = "SELECT available_places FROM flights WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("available_places")>0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    /**
     * Printing some information about the flight
     * */
    private void printSchedule(){
        System.out.println("Flight name : departure time : arrival time");
        String query = "SELECT flight_name,arrival_time,departure_time FROM flights";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getString("flight_name") + " : " + resultSet.getTimestamp("departure_time") + " : " + resultSet.getTimestamp("arrival_time"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
