public class Employee extends Person{
    private final DatabaseManager db = new DatabaseManager();
    private String workingPosition;
    private int salary;
    Employee(String first_name, String last_name, String passport_id, int salary, String workingPosition){
        super(first_name, last_name, passport_id);
        this.salary = salary;
        this.workingPosition = workingPosition;
    }
    @Override
    public int getId(){
        return db.getEmployeeId(this);
    }
    @Override
    public String toString(){
        return
          "first name : " + this.getFirstName()+
          "last name : " + this.getLastName()+
          "passport id : " + this.getPassportId()+
          "salary : " + this.getSalary()+
          "working position : " + this.getWorkingPosition();
    }

    public String getWorkingPosition() {
        return workingPosition;
    }

    public int getSalary() {
        return salary;
    }

}
