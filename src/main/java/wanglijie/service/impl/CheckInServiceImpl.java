package wanglijie.service.impl;

import wanglijie.dao.CheckInDao;
import wanglijie.model.CheckIn;
import wanglijie.service.CheckInService;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public class CheckInServiceImpl implements CheckInService {
    public void checkIn(CheckIn checkIn) throws SQLException {
        new CheckInDao().insertOrUpdate(checkIn);
    }
}
