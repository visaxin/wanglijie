package wanglijie.service;

import wanglijie.model.CheckIn;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public interface CheckInService {
    public void checkIn(CheckIn checkIn) throws SQLException;
}
