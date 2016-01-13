package wanglijie.service.impl;

import wanglijie.dao.EmployeeDao;
import wanglijie.model.Employee;
import wanglijie.service.EmployeeService;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/13/16.
 */
public class EmployeeServiceImpl implements EmployeeService{
    public void addNewEmployee(Employee employee) throws SQLException {
        new EmployeeDao().insertOrUpdate(employee);
    }
}
