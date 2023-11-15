import java.sql.*;
import java.util.*;
public class AirportDatabaseManager {
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
}
