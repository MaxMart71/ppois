

import java.sql.*;
import java.util.*;

public class DatabaseManager {

    /**
     * Adding airline into database
     * @param airline airline that we want to add*/
    public void addNewAirline(Airlines airline) {
        String query = "INSERT INTO airlines (name) VALUES (?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, airline.getName());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting airline id from database
     * @param name name of the airline whose id we want to get
     * @return airlineId id of this airline*/
    public int getAirlineId(String name){
        String query = "SELECT id FROM airlines WHERE name = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, name);

            try (ResultSet result_set = st.executeQuery()) {
                if (result_set.next()) {
                    return result_set.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    /**
     * Deleting airline from database
     * @param airlineId id of the airline that we want to delete*/
    public void deleteAirline(int airlineId){
        String query = "DELETE FROM airline WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,airlineId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adding employee into database
     * @param employee employee that we want to add*/
    public void addNewEmployee(Employee employee) {
        String query = "INSERT INTO employees (first_name, last_name, passport_id, working_position, salary) VALUES (?,?,?,?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, employee.getFirstName());
            st.setString(2, employee.getLastName());
            st.setString(3, employee.getPassportId());
            st.setString(4, employee.getWorkingPosition());
            st.setInt(5, employee.getSalary());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deleting employee from database
     * @param employeeId id of the employee that we want to delete*/
    public void deleteEmployee(int employeeId){
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, employeeId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting employee id from database
     * @param employee person whose id we want to get
     * @return employeeId id of this employee*/
    public int getEmployeeId(Employee employee){
        String query = "SELECT id FROM employees WHERE passport_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, employee.getPassportId());

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
     * Adding airport into database
     * @param airport airport that we want to add*/
    public void addNewAirport(Airport airport){
        String query = "INSERT INTO airport (name, location) VALUES (?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, airport.getName());
            st.setString(2, airport.getLocation());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting airport id from database
     * @param name name of the airport whose id we want to get
     * @return airportId id of this airport*/
    public int getAirportId(String name){
        String query = "SELECT id FROM airport WHERE name = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, name);

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
     * Deleting airport from database
     * @param airportId id of the airport that we want to delete*/
    public void deleteAirport(int airportId){
        String query = "DELETE FROM airport WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, airportId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adding plane into database
     * @param plane plane that we want to add*/
    public void addNewPlane(Plane plane){
        String query = "INSERT INTO planes (model, airlines_id, amount_of_available_places) VALUES (?,?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, plane.getModel());
            st.setInt(2, plane.getAirlines_id());
            st.setInt(3, plane.getAmountOfPlaces());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting plane id from database
     * @param plane plane whose id we want to get
     * @return planeId id of this plane*/
    public int getPlaneId(Plane plane){
        String query = "SELECT id FROM planes WHERE model = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, plane.getModel());

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
     * Deleting plane from database
     * @param planeId id of the plane that we want to delete*/
    public void deletePlane(int planeId){
        String query = "DELETE FROM planes WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adding flight into database
     * @param flight flight that we want to add*/
    public void addNewFlight(Flight flight){
        String query = "INSERT INTO flights (plane_id, departure_airport_id, arrival_airport_id,airlines_id, departure_time, arrival_time,flight_name) VALUES (?,?,?,?,?,?,?)";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flight.getPlaneId());
            st.setInt(2, flight.getDepartureAirportId());
            st.setInt(3, flight.getArrivalAirportId());
            st.setInt(4, flight.getAirlinesId());
            st.setTimestamp(5, flight.getDepartureTime());
            st.setTimestamp(6, flight.getArrivalTime());
            st.setString(7, flight.getFlightName());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deleting flight from database
     * @param flight flight that we want to delete*/
    public void deleteFlight(Flight flight){
        String query = "DELETE FROM flights WHERE (plane_id ,departure_airport_id, arrival_airport_id,airlines_id, departure_time, arrival_time, flight_name) = (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flight.getPlaneId());
            st.setInt(2, flight.getDepartureAirportId());
            st.setInt(3, flight.getArrivalAirportId());
            st.setInt(4, flight.getAirlinesId());
            st.setTimestamp(5, flight.getDepartureTime());
            st.setTimestamp(6, flight.getArrivalTime());
            st.setString(7, flight.getFlightName());
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
    /**
     * Getting plane id by flight id from database
     * @param flightId id of the flight
     * @return planeId id of this plane*/
    private int getPlaneIdByFlightId(int flightId){
        String query = "SELECT plane_id FROM flights WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, flightId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("plane_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    /**
     * Adding passenger into database
     * @param passenger passenger that we want to add*/
    public void addNewPassenger(Passenger passenger) {
        int planeId = getPlaneIdByFlightId(passenger.getFlightId());
        if(isPlacesAvailable(planeId)){
            String query = "INSERT INTO passengers (first_name, last_name, passport_id, flight_id) VALUES (?,?,?,?)";
            try (Connection con = ConnectionManager.open();
                 PreparedStatement st = con.prepareStatement(query)) {
                st.setString(1, passenger.getFirstName());
                st.setString(2, passenger.getLastName());
                st.setString(3, passenger.getPassportId());
                st.setInt(4, passenger.getFlightId());

                st.executeUpdate();
                changingPlacesAmount(planeId);
                addRelationPlanePassenger(planeId,passenger.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println(" ");
        }
    }
    private int getPlaneIdByPassengerId(int passengerId){
        String query = "SELECT plane_id FROM flights JOIN passengers ON flights.id = passengers.flight_id WHERE passengers.id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, passengerId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("plane_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
     * @param passenger passenger whose id we want to get
     * @return passengerId id of this passenger*/
    public int getPassengerId(Passenger passenger){
        String query = "SELECT id FROM passengers WHERE passport_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, passenger.getPassportId());

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
     * Changing amount of available places in the plane
     * @param planeId id of the plane
     * */
    private void changingPlacesAmount(int planeId){
        String query = "UPDATE planes SET amount_of_available_places = amount_of_available_places - 1 WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Checking if there is 1 and more available places in the plane
     * @param planeId id of the plane
     * @return true/false if there is available places/not
     * */
    private boolean isPlacesAvailable(int planeId){
        String query = "SELECT amount_of_available_places FROM planes WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("amount_of_available_places")>0;
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
    /**
     * Adding plane id and passenger id  into database
     * @param (planeId, passengerId) id of the plane and passenger that we want to add*/
    public void addRelationPlanePassenger(int planeId, int passengerId){
        String query = "INSERT INTO plane_passengers (plane_id,passenger_id) VALUES (?,?)";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);
            st.setInt(2, passengerId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Deleting plane id and passenger id from database
     * @param planeId id of the plane that we want to delete*/
    public boolean deleteRelationPlanePassenger(int planeId){
        String query = "DELETE plane_passengers WHERE plane_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);
            return st.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting all passengers id by the plane from database
     * @return planePassengers list that contains all passengers id*/
    public List<Integer> getPassengersByPlane(int planeId){
        List<Integer>planePassengers = new ArrayList<>();
        String query = "SELECT passenger_id FROM plane_passengers WHERE plane_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    planePassengers.add(resultSet.getInt("passenger_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planePassengers;
    }
}

