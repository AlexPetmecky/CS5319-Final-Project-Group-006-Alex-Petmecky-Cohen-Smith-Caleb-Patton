import frontend.mainfront;

public class Main {
	Blackboard blackboard;
	EmployeeDB employeedb;
	
	public Main() {
		blackboard = new Blackboard();
		employeedb = new EmployeeDB(blackboard);
		employeedb.updateDB(1, "firstname", "jane");
	}
	
    public static void main(String[] args) {
		mainfront mf = new mainfront();
		mf.setup();
    	new Main();

    }
    
}
