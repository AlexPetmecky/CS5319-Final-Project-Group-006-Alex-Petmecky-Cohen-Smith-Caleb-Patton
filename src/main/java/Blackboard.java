
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

public class Blackboard {

	private HashMap<Integer, String[]> employees;
	public HashMap<String, Integer> employeeValueToIndexMap;
	
	public Blackboard() {
		initIndexMap();
		employees = new HashMap<Integer, String[]>();

		readEmployeesFromFile();
	}

	private void initIndexMap() {
		employeeValueToIndexMap = new HashMap<String, Integer>();
		employeeValueToIndexMap.put("firstname", 0);
		employeeValueToIndexMap.put("lastname", 1);
		employeeValueToIndexMap.put("hoursworked", 2);
	}
	public synchronized void test(String pr) {
		System.out.println(pr);
	}
	public String[] getEmployeeData(int id) {
		return employees.get(id);
	}
	public boolean writeEmployeeData(int id, String[] updated) {
		if(!employees.containsKey(id)) {
			return false;
		}
		employees.put(id, updated);
		return true;
	}

	public boolean saveEmployeesToFile() {
		// returns true if successful
		return writeEmployeesToFile();
	}

	private boolean writeEmployeesToFile() {
		try {
			FileWriter file = new FileWriter("data/employees.json");
			JSONArray outerArray = new JSONArray();
			//for each value in the hashmap
			for (Integer key : employees.keySet()) {
				JSONObject innerObject = new JSONObject();
				innerObject.put("id", key);
				innerObject.put("firstname", employees.get(key)[employeeValueToIndexMap.get("firstname")]);
				innerObject.put("lastname", employees.get(key)[employeeValueToIndexMap.get("lastname")]);
				innerObject.put("hoursworked", employees.get(key)[employeeValueToIndexMap.get("hoursworked")]);
				outerArray.add(innerObject);
			}
			file.write(outerArray.toJSONString());
			file.close();
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return false;
		}
	}

	private boolean readEmployeesFromFile() {
		// returns true if successful
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("data/employees.json"));
			JSONArray outerArray = (JSONArray) obj;
			for (Object o : outerArray) {
				String[] employeeData = new String[3];
				JSONObject innerObject = (JSONObject) o;

				//get hours worked
				String hoursworked = innerObject.get("hoursworked").toString();
				employeeData[employeeValueToIndexMap.get("hoursworked")] = hoursworked;

				//get firstname
				String firstname = innerObject.get("firstname").toString();
				employeeData[employeeValueToIndexMap.get("firstname")] = firstname;

				//get lastname
				String lastname = innerObject.get("lastname").toString();
				employeeData[employeeValueToIndexMap.get("lastname")] = lastname;

				//get id
				int id = Integer.parseInt(innerObject.get("id").toString());
				employees.put(id, employeeData);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("Failed to load employees: file not found.");
		} catch (IOException e) {
			System.out.println("IOException error occurred.");
		} catch (ParseException e) {
			System.out.println("ParseException error occurred.");
		}
		return false;
	}

	// Old reading from files
	/*
	private void initMap(String directory, HashMap<Integer, String[]> map) {
		// get all files in a directory
		File dir = new File(directory);
		File files[] = dir.listFiles();
		for(File entry : files) {
			List<String> tempList = new ArrayList<>();
			// read line of a file
			try (BufferedReader br = new BufferedReader(new FileReader(entry))) {
	            String line;
	            // add this line to the array assigned to this employee
	            while ((line = br.readLine()) != null) {
	                tempList.add(line);
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading " + e.getMessage());
	        }
			String[] result = tempList.toArray(new String[tempList.size()]);

			// just for getting the number
			String fileName = entry.getName();
            int lastIndex = fileName.lastIndexOf('.');
            String nameWithoutExtension = (lastIndex == -1) ? fileName : fileName.substring(0, lastIndex);
			map.put(Integer.parseInt(nameWithoutExtension), result);
		}
	}
	//*/
}
