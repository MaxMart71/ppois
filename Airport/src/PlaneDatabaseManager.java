import java.sql.*;
import java.util.*;
public class PlaneDatabaseManager {
    /**
     * Adding plane into database
     * @param plane plane that we want to add*/
    public void addNewPlane(Plane plane){
        String query = "INSERT INTO planes (model, airlines_id, amount_of_places, plane_number) VALUES (?,?,?,?)";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, plane.getModel());
            st.setInt(2, plane.getAirlines_id());
            st.setInt(3, plane.getAmountOfPlaces());
            st.setString(4, plane.getPlaneNumber());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Getting plane id from database
     * @param planeNumber number if the plane whose id we want to get
     * @return planeId id of this plane*/
    public int getPlaneId(String planeNumber){
        String query = "SELECT id FROM planes WHERE plane_number = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, planeNumber);

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
    public int getAmountOfPlacesByPlaneId(int planeId){
        String query = "SELECT amount_of_places FROM planes WHERE id = ?";
        try (Connection con = ConnectionManager.open();
             PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, planeId);

            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("amount_of_places");
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
}
