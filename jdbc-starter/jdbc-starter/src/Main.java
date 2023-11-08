public class Main {
    private final static DatabaseManager db = new DatabaseManager();

    public static void main(String[] args) {
//        db.rentCar("3046GU-1",40);
//        db.returnCar("3046GU-1");
//        System.out.println(db.getFinalPrice("3046GU-1"));
//        Car car = new Car("4788PO-3", "audi", 10, "sedan");
//        db.addNewCar(car);
//        Car car1 = new Car("4788PO-4", "audi", 10, "sedan");
//        Car car2 = new Car("4788PO-5", "bmw", 12, "sedan");
//        Car car3 = new Car("4788PO-6", "merc", 14, "sedan");
//        Car car4 = new Car("4788PO-7", "wolksw", 16, "sedan");
//        db.addNewCar(car1);
//        db.addNewCar(car2);
//        db.addNewCar(car3);
//        db.addNewCar(car4);
//        db.changeStatusToAvailable("4788PO-3");
        User user = new User("username", "password", "email@gmail.com");
        User user2 = new User("user", "passwo", "ema@gmail.com");
//        db.addNewUser(user2);
//        user.rentCar("4788PO-7");
        user.returnCar("4788PO-7");
        user2.rentCar("4788PO-7");
//        user2.returnCar("4788PO-7");
//        user.getAllAvailableCars();
//        user.returnCar("4788PO-7");
//        user.getAllAvailableCars();

    }
}
