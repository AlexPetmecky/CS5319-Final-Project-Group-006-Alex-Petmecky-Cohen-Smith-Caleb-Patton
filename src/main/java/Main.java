public class Main {
	Blackboard blackboard;
	EmployeeDB employeedb;
	DateDB datedb;

	public static void main(String[] args) {
		mainfront mf = new mainfront();
		mf.setup();
		new Main();
	}
	
	public Main() {
		blackboard = new Blackboard();
		employeedb = new EmployeeDB(blackboard);
		datedb = new DateDB(blackboard);
	}
	
	public void terminate() {
		blackboard.terminate();
		// add code to close the GUI
		
	}
	
	// methods for GUI to call components
	public boolean clockIn(int id, String date, String time) {
		return employeedb.clockIn(id, date, time);
	}
	public boolean clockOut(int id, String date, String time) {
		return employeedb.clockOut(id, date, time);
	}
	public String getEmployeeStringData(int id) {
		return employeedb.getEmployeeStringData(id);
	}
	
	public String getDateString(String date) {
		return datedb.getDateString(date);
	}
	public String getAllDateStrings() {
		return datedb.getAllDateStrings();
	}
    
}
