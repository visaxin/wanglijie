package wanglijie.dao;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import wanglijie.model.User;
import wanglijie.util.DaoUtil;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 12/29/15.
 */
public class UserDao {
    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<User> userBeanHandler = new BeanHandler<User>(User.class);

    public void insertOrUpdate(User user) throws SQLException {
        String sql = "";
        if (select(user.getId()) != null) {
            sql = "update user set name=?, email=?,password=?,gender=?,role=?,age=? where id = ?";
            queryRunner.update(sql, user.getIdNumber(), user.getUserName(), user.getEmail(),user.getPassword(), user.getGender(), user.getRole(),
                    user.getAge(),user.getId());
        } else {
            sql = "insert into user(id_number,name,email,password,gender,role,age) values(?,?,?,?,?,?,?)";
            queryRunner.update(sql, user.getIdNumber(), user.getUserName(),user.getEmail(), user.getPassword(), user.getGender(), user.getRole(),
                    user.getAge());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from user where id = ?";
        queryRunner.update(sql,id);
    }

    public User select(int id) throws SQLException {
        String sql = "select * from user where id = ?";
        return queryRunner.query(sql,userBeanHandler,id);
    }

    public boolean valid(String email, String password) throws SQLException {
        String sql = "select * from user where email = ?";

        User result = queryRunner.query(sql,userBeanHandler,email);
        if(result!=null &&result.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }
}
