import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Logging {

    private final DatabaseManager databaseManager = new DatabaseManager();

    /*
    * Log in method that compare users username and password with data in database */
    public boolean userLogging() throws NoSuchAlgorithmException {
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:");
        username = scanner.next();
        if (!databaseManager.isUsernameTaken(username)){
            System.out.println("No such username");
            return false;
        }
        else{
            System.out.println("Enter your password:");
            password = scanner.next();
            if (Hash.getHash(password).equals(databaseManager.getPasswordByUsername(username))){
                System.out.println("You logged in successfully");
            }
            else{
                System.out.println("Incorrect password");
                return false;
            }
        }
        return true;
    }
    public boolean userLogging(User user) throws NoSuchAlgorithmException{
        if (databaseManager.isUsernameTaken(user.getUsername()) && Hash.getHash(user.getPassword()).equals(databaseManager.getPasswordByUsername(user.getUsername()))){
            return true;
        }
        else {
            return false;
        }
    }
}
