

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class Blackboard {

	
	String employeeDirectory = "./employees/";
	HashMap<Integer, String[]> employees;
	public HashMap<String, Integer> employeeValueToIndexMap;
	
	public Blackboard() {
		
		employees = new HashMap<Integer, String[]>();
		initMap(employeeDirectory, employees);
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
	// todo: method that saves maps  back into files upon termination
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
}
