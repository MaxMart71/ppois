import org.junit.Test;
import static org.junit.Assert.*;

public class testRegistration {
    private final Registration r = new Registration();

    private final DatabaseManager db = new DatabaseManager();

    @Test
    public void userRegistrationWithIncorrectPassword(){
        User user = new User("max", "yfegino", "max@gmail.com");
        assertFalse(r.userRegistration(user));
    }
    @Test
    public void userRegistrationWithIncorrectEmail(){
        User user = new User("max", "yfegino", "maxgmail.com");
        assertFalse(r.userRegistration(user));
    }
    @Test
    public void userCorrectRegistration(){
        User user = new User("alex", "QWe5$td", "good@gmail.com");
        assertTrue(r.userRegistration(user));
        db.deleteUser(user.getPersonId());
    }
}
