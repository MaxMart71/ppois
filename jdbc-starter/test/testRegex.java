import org.junit.After;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import static org.junit.Assert.*;
public class testRegex {
    private final Regex r = new Regex();
    @Test
    public void testIsAvailablePassword(){
        assertTrue(r.isAvailablePassword("RIVh4v("));
        assertTrue(r.isAvailablePassword("IBuieg54$e"));
        assertTrue(r.isAvailablePassword("kdobvuiUI^7"));
        assertFalse(r.isAvailablePassword("iobu1ib2uiuv2i"));
        assertFalse(r.isAvailablePassword("djkvboivwiaon"));
    }

    @Test
    public void testIsAvailableEmail(){
        assertTrue(r.isAvailableEmail("max@gmail.com"));
        assertTrue(r.isAvailableEmail("igor@mail.ru"));
        assertTrue(r.isAvailableEmail("dvobivobi@gamil.com"));
        assertFalse(r.isAvailableEmail("fefibid.gmail.com"));
        assertFalse(r.isAvailableEmail("@gmail.com"));
        assertFalse(r.isAvailableEmail("max@gmail.comm"));
    }
    @Test
    public void testIsAvailableCarId(){
        assertTrue(r.isAvailableCarId("3758IO-3"));
        assertTrue(r.isAvailableCarId("2187TY-7"));
        assertTrue(r.isAvailableCarId("4246RT-6"));
        assertFalse(r.isAvailableCarId("4246RT-8"));
        assertFalse(r.isAvailableCarId("5672FU0"));
        assertFalse(r.isAvailableCarId("7388YI-P"));
        assertFalse(r.isAvailableCarId("1O12YT-2"));
    }

}
