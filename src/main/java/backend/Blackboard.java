package backend;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Blackboard {
    //database of employees
    private ArrayList<Employee> employees;

    public Blackboard() {
        employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addEmployee() {
        //get the first unused id
        int id = 0;
        while (getEmployee(id) != null) {
            id++;
        }
        Employee employee = new Employee(id);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public void removeEmployee(int id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employees.remove(employee);
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void updateEmployeeStatus(int id, int status) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            employee.setStatus(status);
        }
    }

    public void updateEmployeeStatus(Employee employee, int status) {
        employee.setStatus(status);
    }

    public void updateAllEmployeeStatus(int status) {
        for (Employee employee : employees) {
            employee.setStatus(status);
        }
    }

    //write employees to a json file
    public void writeEmployeesToFile() {
        //code to write employees to a json file
        try {
            FileWriter file = new FileWriter("employees.json");
            JSONArray outerArray = new JSONArray();
            for (Employee employee : employees) {
                JSONObject innerObject = new JSONObject();
                innerObject.put("id", employee.getId());
                innerObject.put("status", employee.getStatus());
                outerArray.add(innerObject);
            }
            file.write(outerArray.toJSONString());
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readEmployeesFromFile() {
        //code to read employees from a json file
        JSONParser parser = new JSONParser();
        //JSONArray a = (JSONArray) parser.parse(new FileReader("employees.json"));
        try {
            Object obj = parser.parse(new FileReader("employees.json"));
            JSONArray outerArray = (JSONArray) obj;
            for (Object o : outerArray) {
                JSONObject innerObject = (JSONObject) o;
                int id = Integer.parseInt(innerObject.get("id").toString());
                int status = Integer.parseInt(innerObject.get("status").toString());
                Employee employee = new Employee(id);
                employee.setStatus(status);
                employees.add(employee);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } catch (ParseException e) {
            System.out.println("An error occurred.");
        }
    }
}
