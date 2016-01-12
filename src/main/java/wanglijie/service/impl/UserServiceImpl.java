package wanglijie.service.impl;

import wanglijie.dao.UserDao;
import wanglijie.model.User;
import wanglijie.service.UserService;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/3/16.
 */
public class UserServiceImpl implements UserService {
    UserDao dao = new UserDao();
    public boolean validUser(String userId, String password) throws SQLException {
        return dao.valid(userId,password);
    }

    public void updateOrInsertUser(User user) throws SQLException {
        dao.insertOrUpdate(user);
    }
}
