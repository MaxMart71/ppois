import java.util.*;
import java.util.regex.Pattern;

public class Registration {

    private final DatabaseManager databaseManager = new DatabaseManager();
    private final Regex regEx = new Regex();
    /*
    * User registration using regular expressions*/
    public boolean userRegistration(){
        String username = "";
        String password = "";
        String email = "";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        boolean flag = false;
        while(!flag){
            username = scanner.next();
            if(databaseManager.isUsernameTaken(username)){
                System.out.println("This username is already taken, try another one");
            }
            else{flag = true;}
        }
        System.out.println("Enter password:");
        while (!regEx.isAvailablePassword(password)){
            password = scanner.next();
            if(!regEx.isAvailablePassword(password)){
                System.out.println("This password is not strong");
            }
        }
        System.out.println("Enter email:");
        while(!regEx.isAvailableEmail(email)){
            email = scanner.next();
            if(!regEx.isAvailableEmail(email)){
                System.out.println("This is incorrect email, try one more time");
            }
        }

        User user = new User(username, password, email);

        databaseManager.addNewUser(user);
        return true;
    }
    public boolean userRegistration(User user){
        if(databaseManager.isUsernameTaken(user.getUsername()) ||
                !(regEx.isAvailablePassword(user.getPassword())) ||
                !(regEx.isAvailableEmail(user.getEmail()))){
            return false;
        }
        else{
            databaseManager.addNewUser(user);
            return true;
        }
    }

}
