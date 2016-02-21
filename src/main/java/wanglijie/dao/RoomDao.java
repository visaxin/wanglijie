package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.pool.ObjectPool;
import wanglijie.Constant;
import wanglijie.model.Room;
import wanglijie.util.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by $Jason.Zhang on 1/4/16.
 */
public class RoomDao {
    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<Room> roomBeanHandler = new BeanHandler<Room>(Room.class);

    public void insertOrUpdate(Room room) throws SQLException {
        String sql = "";
        if (select(room.getId()) != null) {
            sql = "update room set roomId=?, roomType=?,roomPrice=?,roomStatus=? where id = ?";
            queryRunner.update(sql,room.getRoomId(),room.getRoomType(),room.getRoomPrice(),room.getRoomStatus());
        } else {
            sql = "insert into room(roomId,roomType,roomPrice,roomStatus) values(?,?,?,?)";
            queryRunner.update(sql, room.getRoomId(),room.getRoomType(),room.getRoomPrice(),room.getRoomStatus());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from room where id = ?";
        queryRunner.update(sql,id);
    }

    public Room select(int id) throws SQLException {
        String sql = "select * from room where id = ?";
        return queryRunner.query(sql,roomBeanHandler,id);
    }
    public List<Room> allRooms() throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Room>> result = new BeanListHandler<Room>(Room.class);
        String sql = "select * from room";
        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);
    }

    public List<Room>  selectAvailableRooms(String roomStatus,String roomType,String roomPriceStart,String roomPriceEnd,int page,int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Room>> result = new BeanListHandler<Room>(Room.class);
        String sql = "select * from room where roomStatus=?";
        arr.add(roomStatus);
        if (roomType != null) {
            sql += " and roomType=?";
            arr.add(roomType);
        }

        if(roomPriceStart!=null && roomPriceEnd !=null){
            sql += " and roomPrice between ? and ?";
            arr.add(roomPriceStart);
            arr.add(roomPriceEnd);
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

    public Room selectRoomByRoomId(int roomId) throws SQLException {
        String sql = "select * from room where roomId = ?";
        return queryRunner.query(sql,roomBeanHandler,roomId);
    }

}
