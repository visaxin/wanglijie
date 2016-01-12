package wanglijie.service;

import wanglijie.model.User;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/3/16.
 */
public interface UserService {
    public boolean validUser(String userId,String password ) throws SQLException;

    void updateOrInsertUser(User user) throws SQLException;
}
