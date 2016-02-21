package wanglijie.service.impl;

import wanglijie.dao.EmployeeDao;
import wanglijie.model.Employee;
import wanglijie.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/13/16.
 */
public class EmployeeServiceImpl implements EmployeeService{
    public void addNewEmployee(Employee employee) throws SQLException {
        new EmployeeDao().insertOrUpdate(employee);
    }

    public List<Employee> getEmployees(int idNumber, String name, String email, String role) throws SQLException {
       return new EmployeeDao().getEmployees(idNumber,name,email,role);
    }
}
