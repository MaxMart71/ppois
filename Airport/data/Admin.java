public class Admin extends Person {
    private int id;

    public Admin(String firstName, String lastName, String passportId, int id) {
        super(firstName, lastName, passportId);
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "first name : " + this.getFirstName() +
                "last name : " + this.getLastName() +
                "passport id : " + this.getPassportId() +
                "working position : admin";
    }
}
