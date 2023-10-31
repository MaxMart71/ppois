import java.util.*;
import java.util.regex.Pattern;

public class Registration {
    private String username = "";
    private String password = "";
    private String email = "";

    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private final Regex r = new Regex();
    /*
    * User registration using regular expressions*/
    public boolean userRegistration(){
        System.out.println("Enter username:");
        boolean flag = false;
        while(!flag){
            username = scanner.next();
            if(db.isUsernameTaken(username)){
                System.out.println("This username is already taken, try another one");
            }
            else{flag = true;}
        }
        System.out.println("Enter password:");
        while (!r.isAvailablePassword(password)){
            password = scanner.next();
            if(!r.isAvailablePassword(password)){
                System.out.println("This password is not strong");
            }
        }
        System.out.println("Enter email:");
        while(!r.isAvailableEmail(email)){
            email = scanner.next();
            if(!r.isAvailableEmail(email)){
                System.out.println("This is incorrect email, try one more time");
            }
        }

        User user = new User(username, password, email);

        db.addNewUser(user);
        return true;
    }
    public boolean userRegistration(User user){
        if(db.isUsernameTaken(user.getUsername()) ||
                !(r.isAvailablePassword(user.getPassword())) ||
                !(r.isAvailableEmail(user.getEmail()))){
            return false;
        }
        else{
            db.addNewUser(user);
            return true;
        }
    }

}
