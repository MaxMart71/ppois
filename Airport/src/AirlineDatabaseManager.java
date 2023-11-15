import java.sql.*;
import java.util.*;
public class AirlineDatabaseManager {
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
        String query = "DELETE FROM airlines WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1,airlineId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
