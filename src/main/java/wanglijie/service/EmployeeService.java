package wanglijie.service;

import wanglijie.model.Employee;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/13/16.
 */
public interface EmployeeService {
    public void addNewEmployee(Employee employee) throws SQLException;
}
