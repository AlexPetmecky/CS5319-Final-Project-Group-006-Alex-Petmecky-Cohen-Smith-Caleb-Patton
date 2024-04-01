package backend;

public class Employee {
    private int id;
    private EmployeeStatus status;

    public Employee(int id) {
        this.id = id;
        this.status = new EmployeeStatus(EmployeeStatus.OUT);
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status.getStatus();
    }

    public void setStatus(int status) {
        this.status.setStatus(status);
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
