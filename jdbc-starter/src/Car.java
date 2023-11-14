import java.util.ArrayList;
import java.util.List;

public class Car {
    private String car_id;
    private String car_name;
    private int price_per_hour;
    private String type;

    Regex r = new Regex();


    public Car(String car_id, String car_name, int price, String type){
        this.car_name = car_name;
        this.car_id = car_id;
        this.price_per_hour = price;
        this.type = type;
    }

    public String getCarId() {
        return car_id;
    }

    public void setCarId(String car_id) {
        this.car_id = car_id;
    }

    public String getCarName() {
        return car_name;
    }

    public void setCarName(String car_name) {
        this.car_name = car_name;
    }

    public int getPricePerHour() {
        return price_per_hour;
    }

    public void setPricePerHour(int price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    /*
    * A method that return a list of all typer of car
    * @return types list of all car types*/
    public static CarTypes[] getTypes() {
        return CarTypes.values();
    }
}
