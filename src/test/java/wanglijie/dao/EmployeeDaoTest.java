package wanglijie.dao;

import org.junit.Test;
import wanglijie.model.Employee;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by $Jason.Zhang on 1/20/16.
 */
public class EmployeeDaoTest {

    EmployeeDao employeeDao = new EmployeeDao();
    @Test
    public void testInsertOrUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testSelect() throws Exception {

    }

    @Test
    public void testGetEmployees() throws Exception {
        List<Employee> employeeList = employeeDao.getEmployees(123,null,null,null);

        for(int i=0;i<employeeList.size();i++){
            Employee e = employeeList.get(i);
            System.out.println(e.getIdNumber());
        }
    }
}