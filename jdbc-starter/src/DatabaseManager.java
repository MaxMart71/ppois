import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseManager {
        private final int HOUR_IN_MILLISECONDS = 3600000;
        private final String AVAILABLE = "available";
        private final String RENTED = "rented";
    /*

    * Adding user into database
    * @param user person that we want to add
    * */
    public void addNewUser(User user) {
        String query = "INSERT INTO person (username, password, email) VALUES (?,?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, user.getUsername());
            st.setString(2, Hash.getHash(user.getPassword()));
            st.setString(3, user.getEmail());

            st.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * Deleting user from database
     * @param user person that we want to delete
     * */
    public void deleteUser(int user_id) {
        String query = "DELETE FROM person WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, user_id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    * Adding new car into database
    * @param car the car that we want to add
    **/
    public void addNewCar(Car car) {
        String query = "INSERT INTO car (id, carname, price_per_hour, type, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car.getCarId());
            st.setString(2, car.getCarName());
            st.setInt(3, car.getPricePerHour());
            st.setString(4, car.getType());
            st.setString(5, AVAILABLE);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    * Deleting car from database
    *
    * @param carId the number of the car we want to delete
    * */
    public void deleteCar(String carId) {
        String query = "DELETE FROM car WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, carId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    *Getting user_id from database by username
    * @param username
    * @return user_id*/
    public int getUserIdByUsername(String username) {
        String query = "SELECT id FROM person WHERE username = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);

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
    /*
     *Getting password from database by username
     * @param username
     * @return password
     * */
    public String getPasswordByUsername(String username) {
        String query = "SELECT password FROM person WHERE username = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving user password", e);
        }
        return null;
    }
    public String getCarnameByCarId(String car_id){
        String query = "SELECT carname FROM car WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving carname", e);
        }
        return null;
    }
    /*
    * Getting all available cars
    * @return names car_names that are available
    * */
    public List<String> getAvailableCars() {
        List<String> names = new ArrayList<>();
        System.out.println("\nHere are our available cars:");
        String query = """
                SELECT carname FROM car WHERE status = 'available';
                """;
        try(Connection con = ConnectionManager.open();
        PreparedStatement st = con.prepareStatement(query)) {
            try(ResultSet resultSet = st.executeQuery() ){
                while(resultSet.next()){
                    names.add(resultSet.getString("carname"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return names;
    }
    /*
    * Getting username from database by id
    * @param id
    * @return username*/
    public String getUsernameById(int id) {
        String query = "SELECT username FROM person WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving username", e);
        }
        return null;
    }
    public void changeStatusToRented(String car_id){
        String query = "UPDATE car SET status = ? WHERE id = ?";
        try(Connection con = ConnectionManager.open();
        PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, RENTED);
            st.setString(2, car_id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void changeStatusToAvailable(String car_id){
        String query = "UPDATE car SET status = ? WHERE id = ?";
        try(Connection con = ConnectionManager.open();
            PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, AVAILABLE);
            st.setString(2, car_id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isAvailable(String car_id){
        String query = "SELECT status FROM car WHERE id = ?";
        try(Connection con = ConnectionManager.open();
        PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try(ResultSet resultSet = st.executeQuery()){
                if(resultSet.next()){
                    return Objects.equals(resultSet.getString("status"), "available");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    /*Renting car
    * @param car_id , person_id
    * */
    public boolean rentCar(String car_id, int person_id){
        String query_for_rent = "INSERT INTO rent (car_id,person_id, was_rented_date) VALUES (?, ?, NOW())";
        if(isAvailable(car_id)){
            try (Connection con = ConnectionManager.open();
                 PreparedStatement st = con.prepareStatement(query_for_rent)) {
                st.setString(1, car_id);
                st.setInt(2, person_id);
                changeStatusToRented(car_id);
                return st.executeUpdate()==1;
            } catch (SQLException e) {
                throw new RuntimeException("Error when adding information in rent table", e);
            }
        }
        else{
            System.out.println("This car is rented now, sorry");
        }
        return false;
    }
    /*
    * Getting price per hour from database by car_id
    * @param car_id
    * @return price_per_hour*/
    public int getPricePerHourByCarId(String car_id) {
        String query = "SELECT price_per_hour FROM car WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("price_per_hour");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving price per hour", e);
        }
        return 0;
    }
    public long getWasRentedDateByCarId(String car_id){
        List<Long> dates = new ArrayList<>();
        String query = "SELECT was_rented_date FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    dates.add(resultSet.getTimestamp("was_rented_date").getTime());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving was rented date", e);
        }
        return dates.get(dates.size()-1);
    }

    public double getFinalPrice(String car_id){
        return (double)(getWasReturnDateByCarId(car_id) - getWasRentedDateByCarId(car_id)) * getPricePerHourByCarId(car_id) / HOUR_IN_MILLISECONDS;
    }

    /*
    * Method that removes user form car table*/
    public long getWasReturnDateByCarId(String car_id){
        List<Long> dates = new ArrayList<>();
        String query = "SELECT was_return_date FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    dates.add(resultSet.getTimestamp("was_return_date").getTime());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving car_id", e);
        }
        return dates.get(dates.size()-1);
    }
    /*
    * Returning the car by the user
    * @param car_id
    * @return NOW() - was_rented_date*/
    public boolean returnCar(String car_id){
        String query = "UPDATE rent SET was_return_date = NOW() WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            st.executeUpdate();
            changeStatusToAvailable(car_id);
            System.out.println("Final price:" + getFinalPrice(car_id));
            return st.executeUpdate()==1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    * Method that checks if this username is in database or not
    * @param username
    * @return true/false if username in database or not*/
    public boolean isUsernameTaken(String username){
        String query = "SELECT * FROM person WHERE username = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void deleteAfterTest() {
        String query = "TRUNCATE rent CASCADE";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getPersonId(User user) {
        String query = "SELECT id FROM person WHERE email = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1,  user.getEmail());

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

}
