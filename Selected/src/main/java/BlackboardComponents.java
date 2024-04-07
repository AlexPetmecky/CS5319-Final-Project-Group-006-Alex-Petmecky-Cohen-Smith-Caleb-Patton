import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

class BlackboardComponent {
	Blackboard blackboard;
	HashMap<String, Integer> valueMap;
	
	public BlackboardComponent(Blackboard bb) {
		blackboard = bb;
	}
}


class EmployeeDB extends BlackboardComponent {
	public EmployeeDB(Blackboard bb) {
		super(bb);
	}

	//test method
	public synchronized void updateDB(int id, String value, String write) {
		JSONObject employee = blackboard.getEmployeeData(id);
		System.out.println(employee.get(value));
		employee.replace(value, write);
		blackboard.writeEmployeeData(id, employee);
		System.out.println(blackboard.getEmployeeData(id));
	}
	
	// return false if employee is already clocked in
	public synchronized boolean clockIn(int id, String date, String time) {
		JSONObject dateData = blackboard.getDateData(date);
		if(dateData.containsKey(String.valueOf(id))) {
			return false;
		}
		dateData.put(String.valueOf(id), new JSONObject().put("clockin", time));
		return true;
	}
	
	public synchronized boolean clockOut(int id, String date, String time) {
		JSONObject dateData = blackboard.getDateData(date);
		if(!dateData.containsKey(String.valueOf(id))) {
			return false;
		}
		JSONObject employeeDateEntry = (JSONObject)dateData.get(String.valueOf(id));
		JSONObject employeeEntry = blackboard.getEmployeeData(id);
		employeeDateEntry.put("clockout", time);
		dateData.put(date, employeeDateEntry);
		int currentHoursWorked = (int) employeeEntry.get("hoursworked");
		int hoursWorkedToday = (int) employeeDateEntry.get("clockout") - (int) employeeDateEntry.get("clockin");
		employeeEntry.put("hoursworked", currentHoursWorked + hoursWorkedToday);
		blackboard.writeDateData(date, dateData);
		blackboard.writeEmployeeData(id, employeeEntry);
		return true;
		
	}
	
	public synchronized String getEmployeeStringData(int id) {
		JSONObject employee = blackboard.getEmployeeData(id);
		return employee.toString();
	}

	public void saveEmployeesToFile() {
		// returns true if successful
		blackboard.terminate();
	}
}

class DateDB extends BlackboardComponent {
	public DateDB(Blackboard bb) {
		super(bb);
	}
	public synchronized String getDateString(String date) {
		JSONObject dateobject = blackboard.getDateData(date);
		return dateobject.toString();
	}
	public synchronized String getAllDateStrings() {
		JSONObject dateobject = blackboard.getMasterDateObject();
		return dateobject.toString();
	}
}
