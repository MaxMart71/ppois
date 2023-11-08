public class Admin extends Person {

    DatabaseManager db = new DatabaseManager();

    public Admin(String username, String password){
        super(username, password);
    }

    public Admin(String username, String password, String email){
        super(username, password, email);
    }
    /*
    * Adding new car
    * @param car the car that we want to add*/
    public void addNewCar(Car car){
        db.addNewCar(car);
    }
    /*
    * Deleting the car
    * @param car_id the car number*/
    public void deleteCar(String car_id){db.deleteCar(car_id);}
    /*
    * Adding new user
    * @param user the user we want to add*/
    public void addNewUser(User user){db.addNewUser(user);}
    /*
    * Deleting user
    * @param user the user we want to delete*/
    public void deleteUser(int user_id){db.deleteUser(user_id);}

}
