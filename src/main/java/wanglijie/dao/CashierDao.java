package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import wanglijie.Constant;
import wanglijie.model.Cashier;
import wanglijie.util.DaoUtil;
import wanglijie.util.TimeUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class CashierDao {

    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<Cashier> check_inBeanHandler = new BeanHandler<Cashier>(Cashier.class);

    public void insertOrUpdate(Cashier cashier) throws SQLException {
        String sql = "";
        if (select(cashier.getId()) != null) {
            sql = "update cashier set customer_id=?, cash_pledge=?,price=?,days=?,in_day=?,out_day=? where id = ?";
            queryRunner.update(sql, cashier,
                    cashier.getCustomer_id(),
                    cashier.getCashPledge(),
                    cashier.getPrice(),
                    cashier.getDays(),
                    cashier.getIn_day(),
                    cashier.getOut_day());
        } else {
            sql = "insert into cashier(customer_id,cash_pledge,price,days,in_day,out_day) values(?,?,?,?,?,?)";
            queryRunner.update(sql, cashier,
                    cashier.getCustomer_id(),
                    cashier.getCashPledge(),
                    cashier.getPrice(),
                    cashier.getDays(),
                    cashier.getIn_day(),
                    cashier.getOut_day());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from cashier where id = ?";
        queryRunner.update(sql, id);
    }

    public Cashier select(int id) throws SQLException {
        String sql = "select * from cashier where id = ?";
        return queryRunner.query(sql, check_inBeanHandler, id);
    }

    /**
     * @param dateTimeStart unit: seconds
     * @param dateTimeEnd
     * @param page          page number
     * @param limit         page size
     * @return
     * @throws SQLException
     */
    public List<Cashier> selectCashier(long dateTimeStart, long dateTimeEnd, int page, int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Cashier>> result = new BeanListHandler<Cashier>(Cashier.class);
        String sql = "select * from cashier where update_time between ? and ?";
        arr.add(TimeUtil.toDateTimeFromUnixTimeStamp(dateTimeStart));
        arr.add(TimeUtil.toDateTimeFromUnixTimeStamp(dateTimeEnd));

        if (limit > Constant.MAX_LIMIT) {
            limit = Constant.MAX_LIMIT;
        }

        sql += " limit ?,?";
        arr.add(page * limit);
        arr.add(limit);

        return queryRunner.query(sql, result, arr);
    }
}
