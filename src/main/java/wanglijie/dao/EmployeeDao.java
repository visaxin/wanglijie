package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import wanglijie.Constant;
import wanglijie.model.Employee;
import wanglijie.util.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/10/16.
 */
public class EmployeeDao {

    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<Employee> employeeBeanHandler = new BeanHandler<Employee>(Employee.class);

    public void insertOrUpdate(Employee employee) throws SQLException {
        String sql = "";
        if (select(employee.getId()) != null) {
            sql = "update employee set id_number=?, name=?,email=?,gender=?,role=?,age=?,salary=? where id = ?";
            queryRunner.update(sql, employee.getIdNumber(), employee.getName(), employee.getEmail(), employee.getGender(),
                    employee.getRole(), employee.getAge(), employee.getSalary());
        } else {
            sql = "insert into employee(id_number,name,email,gender,role,age,salary) values(?,?,?,?,?,?,?)";
            queryRunner.update(sql, employee.getIdNumber(), employee.getName(), employee.getEmail(), employee.getGender(),
                    employee.getRole(), employee.getAge(), employee.getSalary());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from employee where id = ?";
        queryRunner.update(sql, id);
    }

    public Employee select(int id) throws SQLException {
        String sql = "select * from employee where id = ?";
        return queryRunner.query(sql, employeeBeanHandler, id);
    }

/*
    public List<Employee> selectAvaiableEmployees(String employeeStatus, String employeeType, String employeePriceStart, String employeePriceEnd, int page, int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Employee>> result = new BeanListHandler<Employee>(Employee.class);
        String sql = "select * from employee where employee_status=?";
        arr.add(employeeStatus);
        if (employeeType != null) {
            sql += " and employee_type=?";
            arr.add(employeeType);
        }

        if (employeePriceStart != null && employeePriceEnd != null) {
            sql += " and employee_price between ? and ?";
            arr.add(employeePriceStart);
            arr.add(employeePriceEnd);
        }
        if (limit > Constant.MAX_LIMIT) {
            limit = Constant.MAX_LIMIT;
        }
        sql += " limit ?,?";
        arr.add(page * limit);
        arr.add(limit);

        return queryRunner.query(sql, result, arr);
    }

    public List<Employee> selectAvaiableEmployees(String[] employeeStatus, String[] employeeType, String employeePriceStart, String employeePriceEnd, int page, int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Employee>> result = new BeanListHandler<Employee>(Employee.class);
        String sql = "select * from employee where employee_status=?";
        arr.add(employeeStatus);
        if (employeeStatus != null) {
            for (int i = 0; i < employeeStatus.length; i++) {
                sql += " or employee_status=?";
                arr.add(employeeStatus[i]);
            }
        }

        if (employeeType != null) {
            for (int i = 0; i < employeeType.length; i++) {
                sql += " or employee_type=?";
                arr.add(employeeType[i]);
            }
        }

        if (employeePriceStart != null && employeePriceEnd != null) {
            sql += " and employee_price between ? and ?";
            arr.add(employeePriceStart);
            arr.add(employeePriceEnd);
        }
        if (limit > Constant.MAX_LIMIT) {
            limit = Constant.MAX_LIMIT;
        }
        sql += " limit ?,?";
        arr.add(page * limit);
        arr.add(limit);

        return queryRunner.query(sql, result, arr);
    }*/
}
