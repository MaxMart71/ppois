public class Passenger extends Person {
    private int flightId;
    private final DatabaseManager db = new DatabaseManager();
    Passenger(String first_name, String last_name, String passport_id , int flightId){
        super(first_name, last_name, passport_id);
        this.flightId = flightId;
    }

    @Override
    public int getId() {
        return db.getPassengerId(this);
    }

    @Override
    public String toString(){
        return
                "first name : " + this.getFirstName() +
                "last name : " + this.getLastName() +
                "passport id : " + this.getPassportId() +
                "flight id : " + this.getFlightId();
    }

    public int getFlightId() {
        return flightId;
    }
}
