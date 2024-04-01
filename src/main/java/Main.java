import backend.Blackboard;
import backend.EmployeeStatus;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Blackboard blackboard = new Blackboard();
        blackboard.readEmployeesFromFile();
        blackboard.updateAllEmployeeStatus(EmployeeStatus.IN);
        blackboard.writeEmployeesToFile();
    }
}
