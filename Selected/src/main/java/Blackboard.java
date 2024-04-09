
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
import java.time.LocalTime;
import java.time.LocalDate;

public class Blackboard implements SubmitHandler{

	// store all data entries
	// so that higher level components can simply get access to the database object by name
	HashMap<String, DataEntry> directory;
	
	// store these for easier editing if needed
	String employeeDirectory = "data/employees.json";
	String dateDirectory = "data/dates.json";
	
	
	public Blackboard() {
		directory = new HashMap<String, DataEntry>();
		directory.put("employees", new DataEntry(employeeDirectory));
		directory.put("dates", new DataEntry(dateDirectory));



		directory.keySet();

	}




	
	
	// runs terminate for each database entry
	public void terminate() {
		for(DataEntry entry : directory.values()) {
			entry.terminate();
		}
	}
	
	
	
	// methods to be used by components to get and write data to databases
	public JSONObject getEmployeeData(int id) {
		return (JSONObject)directory.get("employees").array.get(id);
	}
	public synchronized boolean writeEmployeeData(int id, JSONObject updated) {
		JSONArray array = directory.get("employees").array;
		if(id > array.size() ){
			return false;
		}
		array.add(id, updated);
		directory.get("employees").array = array;
		return true;
	}
	
	public JSONArray getAllEmployees() {
		return (JSONArray)directory.get("employees").array;
	}
	
	public JSONObject getDateData(String date) {
		JSONObject object = (JSONObject)directory.get("dates").array.get(0);

		if(!object.containsKey(date)) {
			object.put(date, new JSONObject());
		}
		return (JSONObject)object.get(date);
	}
	public synchronized void writeDateData(String date, JSONObject updated) {
		((JSONObject)directory.get("dates").array.get(0)).put(date, updated);
	}
	public JSONObject getMasterDateObject() {
		return (JSONObject)directory.get("dates").array.get(0);
	}
	public synchronized void test(String pr) {
		System.out.println(pr);
	}


	@Override
	public void submitIn(String idNum) {
		System.out.println("SUBMITTED IN FROM BLACKBOARD");
		//writeEmployeeData(idNum))
	}

	@Override
	public void submitOut(String idNum) {
		System.out.println("SUBMITTED OUT FROM BLACKBOARD");
	}
}


// these are objects for each database entry (employees, dates)
class DataEntry{
	public JSONArray array;
	private String directory;
	public DataEntry(String dir) {
		directory = dir;
		JSONParser parser = new JSONParser();
		try {
			array = (JSONArray)(parser.parse(new FileReader(directory)));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// run for each data entry on process termination
	// saves the updates contents back to a file
	public void terminate() {
		FileWriter writer;
		try {
			writer = new FileWriter(directory);
			writer.write(array.toString());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
