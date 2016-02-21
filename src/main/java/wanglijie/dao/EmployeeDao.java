package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
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
            sql = "update employee set idNumber=?, name=?,email=?,gender=?,role=?,age=?,salary=? where id = ?";
            queryRunner.update(sql, employee.getIdNumber(), employee.getName(), employee.getEmail(), employee.getGender(),
                    employee.getRole(), employee.getAge(), employee.getSalary());
        } else {
            sql = "insert into employee(idNumber,name,email,gender,role,age,salary) values(?,?,?,?,?,?,?)";
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

    public List<Employee> getEmployees(int idNumber, String name, String email, String role) throws SQLException {
        String sql = "select * from employee where 1=1";
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Employee>> result = new BeanListHandler<Employee>(Employee.class);

        if (idNumber!=0){
            sql += " and idNumber = ?";
            arr.add(idNumber);
        }

        if( name !=null){
            sql += " and name = ?";
            arr.add(name);
        }
        if(email!=null){
            sql+=" and email =  ?";
            arr.add(email);
        }

        if(role !=null){
            sql += " and role = ?";
            arr.add(role);
        }

        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);

    }
}
