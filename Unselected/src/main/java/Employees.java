import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Employees {
    JSONArray employees = new JSONArray();

    String directory = "data/employees.json";

    //constructor
    public Employees() {
        getEmployees();
    }

    //get employees from file
    public void getEmployees() {
        try {
            JSONParser parser = new JSONParser();
            employees = (JSONArray) parser.parse(new FileReader(directory));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //write all employees to file
    public void writeEmployees() {
        JSONParser parser = new JSONParser();
        try {
            employees = (JSONArray)(parser.parse(new FileReader(directory)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //print number of employees
    public void printEmployees() {
        System.out.println("Number of employees: " + employees.size());
    }
}
