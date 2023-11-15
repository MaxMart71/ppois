import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class testAdmin {
    private final String FIRSTNAME = "firstname";
    private final String LASTTNAME = "lastname";
    private final String PASSPORTID = "FUEE839VI5";
    private final int ID = 1;
    private final Admin admin = new Admin(FIRSTNAME, LASTTNAME, PASSPORTID, ID);
    @Test
    public void testGetters(){
        assertEquals(FIRSTNAME, admin.getFirstName());
        assertEquals(LASTTNAME, admin.getLastName());
        assertEquals(PASSPORTID, admin.getPassportId());
        assertEquals(ID, admin.getId());
    }
}
