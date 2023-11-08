
public class Regex {
    private static final String reg_for_password = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{4,16}$";
    private static final String reg_for_email = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

    private static final String reg_for_car_id = "^[0-9]{4}[A-Z]{2}-[0-7]";

    /*
    *Checking if password is strong using regular expressions
    * @param password the password that we want to check is it strong
    * @return true/false if the password strong or not*/
    public boolean isAvailablePassword(String password){
        return password.matches(reg_for_password);
    }
    /*
     *Checking if email is correct using regular expressions
     * @param email the email that we want to check is it correct
     * @return true/false if the email is correct/not*/
    public boolean isAvailableEmail(String email){
        return email.matches(reg_for_email);
    }
    /*
     *Checking if car number is correct using regular expressions
     * @param car_id the car number that we want to check is it correct
     * @return true/false if the car number is correct/not*/
    public boolean isAvailableCarId(String car_id){
        return car_id.matches(reg_for_car_id);
    }
}
