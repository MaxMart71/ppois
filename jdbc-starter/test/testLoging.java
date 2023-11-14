import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static org.junit.Assert.*;
public class testLoging {
    private final Logging l = new Logging();
    private final DatabaseManager db = new DatabaseManager();

    @Test
    public void testLoging() throws NoSuchAlgorithmException{
        User user = new User("oil", "uebg*^Y","dubi@gamil.com");
        db.addNewUser(user);
        assertTrue(l.userLogging(user));
        db.deleteUser(db.getPersonId(user));
    }
    @Test
    public void failedLogging1() throws NoSuchAlgorithmException{
        User user = new User("oil", "uebg*^Y","dubi@gamil.com");
        db.addNewUser(user);
        User user2 = new User("ma", "uebg*^Y","dubi@gamil.com");
        assertFalse(l.userLogging(user2));
        db.deleteUser(db.getPersonId(user));
    }
    @Test
    public void failedLogging2() throws NoSuchAlgorithmException{
        User user = new User("oil", "uebg*^Y","dubi@gamil.com");
        db.addNewUser(user);
        User user2 = new User("max", "uebg*^T","dubi@gamil.com");
        assertFalse(l.userLogging(user2));
        db.deleteUser(db.getPersonId(user));
    }
}

