package backend;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class Blackboard {
    //database of employees
    private ArrayList<Employee> employees;

    public Blackboard() {
        employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
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
    public void writeEmployeesToFile(String filename) {
        //code to write employees to a json file
        try {
            FileWriter file = new FileWriter("employees.json");
            for (Employee employee : employees) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", employee.getId());
                jsonObject.put("status", employee.getStatus().getStatus());
                file.write(jsonObject.toJSONString());
            }
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
