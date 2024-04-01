import java.util.HashMap;

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
		valueMap = blackboard.employeeValueToIndexMap;
	}

	//test method
	public synchronized void updateDB(int id, String value_name, String value) {
		String[] employee = blackboard.getEmployeeData(id);
		int index = valueMap.get(value_name);
		System.out.println(employee[index]);
		employee[index] = value;
		blackboard.writeEmployeeData(id, employee);
		System.out.println(employee[index]);
	}

	public boolean saveEmployeesToFile() {
		// returns true if successful
		return blackboard.saveEmployeesToFile();
	}
}
