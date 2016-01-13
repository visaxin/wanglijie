package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import wanglijie.Constant;
import wanglijie.model.CheckIn;
import wanglijie.util.DaoUtil;
import wanglijie.util.TimeUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class CheckInDao {
    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<CheckIn> check_inBeanHandler = new BeanHandler<CheckIn>(CheckIn.class);

    public void insertOrUpdate(CheckIn checkIn) throws SQLException {
        String sql = "";
        if (select(checkIn.getId()) != null) {
            sql = "update check_in set customerId=?, roomId=?,customerNumber=? where id = ?";
            queryRunner.update(sql,checkIn,checkIn.getCustomerId(),checkIn.getRoomId(),checkIn.getCustomerNumber());
        } else {
            sql = "insert into check_in(customerId,roomId,customerNumber) values(?,?,?)";
            queryRunner.update(sql, checkIn,checkIn.getCustomerId(),checkIn.getRoomId(),checkIn.getCustomerNumber());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from check_in where id = ?";
        queryRunner.update(sql,id);
    }

    public CheckIn select(int id) throws SQLException {
        String sql = "select * from check_in where id = ?";
        return queryRunner.query(sql,check_inBeanHandler,id);
    }

    /**
     *
     * @param dateTimeStart unit: seconds
     * @param dateTimeEnd
     * @param page page number
     * @param limit page size
     * @return
     * @throws SQLException
     */
    public List<CheckIn> selectCheckedIn(long dateTimeStart, long dateTimeEnd,int page, int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<CheckIn>> result = new BeanListHandler<CheckIn>(CheckIn.class);
        String sql = "select * from check_in where updateTime between ? and ?";
        arr.add(TimeUtil.toDateTimeFromUnixTimeStamp(dateTimeStart));
        arr.add(TimeUtil.toDateTimeFromUnixTimeStamp(dateTimeEnd));

        if(limit > Constant.MAX_LIMIT){
            limit = Constant.MAX_LIMIT;
        }

        sql+=" limit ?,?";
        arr.add(page*limit);
        arr.add(limit);

        return queryRunner.query(sql,result,arr);
    }
}
