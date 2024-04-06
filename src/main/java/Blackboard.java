
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

public class Blackboard {

	// store all data entries
	HashMap<String, DataEntry> directory;
	
	// store these for easier editing if needed
	String employeeDirectory = "data/employees.json";
	String dateDirectory = "data/dates.json";
	
	
	public Blackboard() {
		directory = new HashMap<String, DataEntry>();
		directory.put("employees", new DataEntry(employeeDirectory));
		directory.put("dates", new DataEntry(dateDirectory));
	}

	
	
	
	public void terminate() {
		for(DataEntry entry : directory.values()) {
			entry.terminate();
		}
	}
	
	
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
	
	public JSONObject getDateData(String date) {
		JSONObject object = (JSONObject)directory.get("dates").array.getFirst();
		if(!object.containsKey(date)) {
			object.put(date, new JSONObject());
		}
		return (JSONObject)object.get(date);
	}
	public synchronized void writeDateData(String date, JSONObject updated) {
		((JSONObject)directory.get("dates").array.getFirst()).put(date, updated);
	}
	public synchronized void test(String pr) {
		System.out.println(pr);
	}
	

}

class DataEntry{
	public JSONArray array;
	private String directory;
	
	public DataEntry(String dir) {
		directory = dir;
		JSONParser parser = new JSONParser();
		try {
			// probably want to add something here that creates the file if it doesn't exist
			array = (JSONArray)(parser.parse(new FileReader(directory)));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
