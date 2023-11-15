import java.sql.*;
import java.util.*;
public class EmployeeDatabaseManager {
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
}
