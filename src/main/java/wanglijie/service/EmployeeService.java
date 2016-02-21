package wanglijie.service;

import wanglijie.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/13/16.
 */
public interface EmployeeService {
    public void addNewEmployee(Employee employee) throws SQLException;

    List<Employee> getEmployees(int idNumber, String name, String email, String role) throws SQLException;
}
