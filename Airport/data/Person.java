import java.beans.Expression;

public abstract class Person {
    private final String firstName;
    private final String lastName;
    private final String passportId;
    public Person(String firstName, String lastName, String passportId){
        this.firstName = firstName;
        this.lastName = lastName;
        try{
            if(passportId.isEmpty()){
                throw new EmptyPassportException("Password can't be an empty string");
            }
            this.passportId = passportId;
        } catch (EmptyPassportException e) {
            throw new RuntimeException(e);
        }


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
