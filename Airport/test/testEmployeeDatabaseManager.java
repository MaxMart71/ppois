import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testEmployeeDatabaseManager {
    private final EmployeeDatabaseManager employeeDb = new EmployeeDatabaseManager();
    private final Employee employee = new Employee("firstName", "lastName","DBOE23BUIDIV32", 10000, "pilot");
    @Test
    public void testAddNewEmployee() throws SQLException{
        employeeDb.addNewEmployee(employee);
        try (Connection con = ConnectionManager.open();
             Statement st = con.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM employees WHERE passport_id = '" + employee.getPassportId() + "'")) {
            assertTrue(resultSet.next());
            assertEquals(employee.getFirstName(), resultSet.getString("first_name"));
            assertEquals(employee.getLastName(), resultSet.getString("last_name"));
            assertEquals(employee.getId(), resultSet.getInt("id"));
            assertEquals(employee.getWorkingPosition(), resultSet.getString("working_position"));
            assertEquals(employee.getPassportId(), resultSet.getString("passport_id"));
            employeeDb.deleteEmployee(employee.getId());
        }
    }
}
