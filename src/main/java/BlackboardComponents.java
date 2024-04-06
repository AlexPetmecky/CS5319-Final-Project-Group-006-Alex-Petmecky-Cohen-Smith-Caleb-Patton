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

	public void saveEmployeesToFile() {
		// returns true if successful
		blackboard.terminate();
	}
}
