import org.junit.runner.Request;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
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
        String query = "INSERT INTO car (id, carname, price_per_hour, type) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car.getCarId());
            st.setString(2, car.getCarName());
            st.setInt(3, car.getPricePerHour());
            st.setString(4, car.getType());

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
            throw new RuntimeException("Error when retrieving user password", e);
        }
        return null;
    }
    /*
    * Getting all available cars
    * @return names car_names that are available
    * */
    public List<String> getAvailableCars() {
        List<String> names = new ArrayList<>();
        System.out.println("\nHere are our available cars, type the car ID to rent the car:");
        String query = """
                SELECT carname FROM car;
                """;
        String query_for_check = "SELECT car_id FROM rent";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    names.add(resultSet.getString("carname"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection con = ConnectionManager.open();
        PreparedStatement st = con.prepareStatement(query_for_check)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                names.remove(getCarnameByCarId(resultSet.getString("car_id")));
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
    public int getUserIdByCarId(String car_id){
        String query = "SELECT person_id FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("person_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving username", e);
        }
        return 0;
    }
    /*Renting car
    * @param car_id , person_id
    * */
    public void rentCar(String car_id, int person_id){
        String query_for_rent = "INSERT INTO rent (car_id,person_id, was_rented_date) VALUES (?, ?, NOW())";
        try (Connection con = ConnectionManager.open();
        PreparedStatement st = con.prepareStatement(query_for_rent)) {
            st.setString(1, car_id);
            st.setInt(2, person_id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error when adding information in rent table", e);
        }
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
            throw new RuntimeException("Error when retrieving username", e);
        }
        return 0;
    }
    public String getCarIdByWasRentedDate(Timestamp was_rented_date){
        String query = "SELECT car_id FROM rent WHERE was_rented_date = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setTimestamp(1, was_rented_date);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("car_id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving user password", e);
        }
        return null;
    }

    public Timestamp getWasRentedDateByCarId(String car_id){
        String query = "SELECT was_rented_date FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getTimestamp("was_rented_date");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving user password", e);
        }
        return null;
    }

    public double getCurrentPrice(String car_id){
        String query = "SELECT NOW()-was_rented_date FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return (double) resultSet.getTimestamp("was_rented_date").getTime() /3600000*getPricePerHourByCarId(car_id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    /*
    * Method that removes user form car table*/
    public Timestamp getWasReturnDateByCarId(String car_id){
        String query = "SELECT was_return_date FROM rent WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getTimestamp("was_return_date");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error when retrieving user password", e);
        }
        return null;
    }
    /*
    * Returning the car by the user
    * @param car_id
    * @return NOW() - was_rented_date*/
    public void returnCar(String car_id){
        String query = "UPDATE rent SET was_return_date = NOW() WHERE car_id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, car_id);
            st.executeUpdate();
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
