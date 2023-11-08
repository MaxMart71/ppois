import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Logging {
    private String username;
    private String password;

    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private final Regex r = new Regex();

    /*
    * Log in method that compare users username and password with data in database */
    public boolean userLogging() throws NoSuchAlgorithmException {
        System.out.println("Enter your username:");
        username = scanner.next();
        if (!db.isUsernameTaken(username)){
            System.out.println("No such username");
        }
        else{
            System.out.println("Enter your password:");
            password = scanner.next();
            if (Hash.getHash(password).equals(db.getPasswordByUsername(username))){
                System.out.println("You logged in successfully");
            }
            else{
                System.out.println("Incorrect password");
            }
        }
        return true;
    }
    public boolean userLogging(User user) throws NoSuchAlgorithmException{
        if (db.isUsernameTaken(user.getUsername()) && Hash.getHash(user.getPassword()).equals(db.getPasswordByUsername(user.getUsername()))){
            return true;
        }
        else {
            return false;
        }
    }
}
