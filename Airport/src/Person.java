public abstract class Person {
    private final String firstName;
    private final String lastName;
    private final String passportId;
    public Person(String firstName, String lastName, String passportId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
    }

    public String getPassportId() {
        return passportId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public abstract int getId();

}
