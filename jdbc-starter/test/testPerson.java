import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
public class testPerson {
    private final int id = 3;
    private final String username = "max";
    private final String password = "QWert#1";
    private final String email = "max@gmail.com";
    private Person person = new Person(0,"", "", "");

    @Test
    public void testSetId(){
        person.setId(id);
        assertEquals(person.getId(), id);
    }
    @Test
    public void testSetUsername(){
        person.setUsername(username);
        assertEquals(person.getUsername(), username);
    }
    @Test
    public void testSetPassword(){
        person.setPassword(password);
        assertEquals(person.getPassword(), password);
    }
    @Test
    public void testSetEmail(){
        person.setEmail(email);
        assertEquals(person.getEmail(), email);
    }

}
