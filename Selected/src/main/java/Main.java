public class Main {
	Blackboard blackboard;
	EmployeeDB employeedb;
	DateDB datedb;

	public static void main(String[] args) {
		mainfront mf = new mainfront();
		mf.setup(new Main());
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
	
	public String formatJsonString(String json) {
		StringBuilder formattedJson = new StringBuilder();
        int indentLevel = 0;
        boolean inQuotes = false;
        json = "<html>" + json + "</html>";
		for (char c : json.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                    formattedJson.append(c);
                    if (!inQuotes) {
                        indentLevel++;
                        appendIndent(formattedJson, indentLevel);
                    }
                    break;
                case '}':
                case ']':
                    if (!inQuotes) {
                        indentLevel--;
                        appendIndent(formattedJson, indentLevel);
                    }
                    formattedJson.append(c);
                    break;
                case '"':
                    formattedJson.append(c);
                    inQuotes = !inQuotes;
                    break;
                case ',':
                    formattedJson.append(c);
                    if (!inQuotes) {
                        formattedJson.append("<br/>");
                        appendIndent(formattedJson, indentLevel);
                    }
                    break;
                default:
                    formattedJson.append(c);
            }
        }

        return formattedJson.toString();
	}
	
	private static void appendIndent(StringBuilder stringBuilder, int indentLevel) {
        for (int i = 0; i < indentLevel; i++) {
            stringBuilder.append("    "); // 4 spaces per indent level
        }
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
	public String getAllEmployeesString() {
		return employeedb.getAllEmployeesString();
	}
	public String getDateString(String date) {
		return datedb.getDateString(date);
	}
	public String getAllDateStrings() {
		return datedb.getAllDateStrings();
	}
    
}
