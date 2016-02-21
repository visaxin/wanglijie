package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import wanglijie.Constant;
import wanglijie.model.PosProcess;
import wanglijie.util.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/4/16.
 */
public class PosDao {
    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<PosProcess> posBeanHandler = new BeanHandler<PosProcess>(PosProcess.class);

    public void insertOrUpdate(PosProcess pos) throws SQLException {
        String sql = "";
        if (select(pos.getId()) != null) {
            sql = "update pos_sell set posId=?, roomNumber=?,numberPrice=?,roomStatus=? ,realIncome = ? where id = ?";
            queryRunner.update(sql,pos.getPosId(),pos.getRoomNumber(),pos.getRoomPrice(),pos.getRoomStatus(),pos.getRealIncome());
        } else {
            sql = "insert into pos_sell(posId,roomNumber,roomPrice,roomStatus,realIncome) values(?,?,?,?,?)";
            queryRunner.update(sql,pos.getPosId(),pos.getRoomNumber(),pos.getRoomPrice(),pos.getRoomStatus(),pos.getRealIncome());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from pos_sell where id = ?";
        queryRunner.update(sql,id);
    }

    public PosProcess select(int id) throws SQLException {
        String sql = "select * from pos_sell where id = ?";
        return queryRunner.query(sql,posBeanHandler,id);
    }
    public List<PosProcess> allPosProcesss() throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<PosProcess>> result = new BeanListHandler<PosProcess>(PosProcess.class);
        String sql = "select * from pos_sell";
        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);
    }

    public List<PosProcess>  selectAvailablePosProcesss(String posStatus,String posType,String posPriceStart,String posPriceEnd,int page,int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<PosProcess>> result = new BeanListHandler<PosProcess>(PosProcess.class);
        String sql = "select * from pos where posStatus=?";
        arr.add(posStatus);
        if (posType != null) {
            sql += " and posType=?";
            arr.add(posType);
        }

        if(posPriceStart!=null && posPriceEnd !=null){
            sql += " and posPrice between ? and ?";
            arr.add(posPriceStart);
            arr.add(posPriceEnd);
        }
        if(limit > Constant.MAX_LIMIT){
            limit = Constant.MAX_LIMIT;
        }
        sql+=" limit ?,?";
        arr.add(page*limit);
        arr.add(limit);
        System.out.println("Searching sql: " +sql);
        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);
    }

    public PosProcess selectPosProcessByPosProcessId(int posId) throws SQLException {
        String sql = "select * from pos where posId = ?";
        return queryRunner.query(sql,posBeanHandler,posId);
    }

}
