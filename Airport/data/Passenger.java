public class Passenger extends Person {
    private final PassengerDatabaseManager passengerDb = new PassengerDatabaseManager();
    Passenger(String first_name, String last_name, String passport_id){
        super(first_name, last_name, passport_id);
    }

    @Override
    public int getId() {
        return passengerDb.getPersonId(this);
    }

    @Override
    public String toString(){
        return
                "first name : " + this.getFirstName() +
                "last name : " + this.getLastName() +
                "passport id : " + this.getPassportId();
    }
}
