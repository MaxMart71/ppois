import java.util.List;

public class User extends Person{

    DatabaseManager db = new DatabaseManager();
    public User(int id, String username, String password, String email){
      super(id, username, password, email);
    }

    public User(String username, String password, String email){
        super(username, password, email);
    }

    public void rentCar(String car_id){
        db.rentCar(car_id, this.getPersonId());
    }

    public void returnCar(String car_id){
        db.returnCar(car_id);
    }

    /*
    * Method that shows all types of cars*/
    public static void getALlTypesOfCars(){
        CarTypes[] types = Car.getTypes();
        for(var type : types){
            System.out.println(type);
        }
    }
    /*
    * Method that shows all cars, that are available*/
    public void getAllAvailableCars(){
        List<String> cars = db.getAvailableCars();
        for (var car : cars){
            System.out.println(car);
        }
    }


}
