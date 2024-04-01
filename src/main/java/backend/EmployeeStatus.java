package backend;

public class EmployeeStatus {
    private int status;

    public EmployeeStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static int OUT = 0;
    public static int IN = 1;
}
