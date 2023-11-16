import java.sql.*;
import java.util.*;
public class PassengerDatabaseManager {
    private final FlightDatabaseManager flightDb = new FlightDatabaseManager();
    /**
     * Adding passenger into database
     * @param person passenger that we want to add*/
    public void addNewPassenger(Person person, int flightId) {
        if(flightDb.isPlacesAvailable(flightId)){
            String query = "INSERT INTO passengers (first_name, last_name, passport_id) VALUES (?,?,?)";
            try (Connection con = ConnectionManager.open();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, person.getFirstName());
                st.setString(2, person.getLastName());
                st.setString(3, person.getPassportId());

                st.executeUpdate();
                flightDb.changingPlacesAmount(flightId);
                addPassengerOnFlight(flightId, getPersonId(person));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println(" ");
        }
    }
    /**
     * Deleting passenger from database
     * @param passengerId id of the passenger that we want to delete*/
    public void deletePassenger(int passengerId){
        String query = "DELETE FROM passengers WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, passengerId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting passenger id from database
     * @param person passenger whose id we want to get
     * @return passengerId id of this passenger*/
    public int getPersonId(Person person){
        String query = "SELECT id FROM passengers WHERE passport_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, person.getPassportId());

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
    /**
     * Adding plane id and passenger id  into database
     * @param (flightId, passengerId) id of the plane and passenger that we want to add*/
    public void addPassengerOnFlight(int flightId, int passengerId){
        String query = "INSERT INTO passengers_on_flight (flight_id,person_id) VALUES (?,?)";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);
            st.setInt(2, passengerId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deleting plane id and passenger id from database
     * @param flightId id of the plane that we want to delete*/
    public boolean deletePassengerFromFlight(int flightId){
        String query = "DELETE FROM passengers_on_flight WHERE flight_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);
            return st.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting all passengers id by the plane from database
     * @return planePassengers list that contains all passengers id*/
    public List<Integer> getPassengersOnFlight(int flightId){
        List<Integer>planePassengers = new ArrayList<>();
        String query = "SELECT person_id FROM passengers_on_flight WHERE flight_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    planePassengers.add(resultSet.getInt("person_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planePassengers;
    }
}
