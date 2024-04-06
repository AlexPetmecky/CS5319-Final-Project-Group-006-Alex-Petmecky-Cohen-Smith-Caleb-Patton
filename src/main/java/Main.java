import frontend.mainfront;

public class Main {
	Blackboard blackboard;
	EmployeeDB employeedb;

	public static void main(String[] args) {
		mainfront mf = new mainfront();
		mf.setup();
		new Main();
	}
	
	public Main() {
		blackboard = new Blackboard();
		employeedb = new EmployeeDB(blackboard);
	}
	
	public void terminate() {
		blackboard.terminate();
		// add code to close the GUI
		
	}
    
}
