package wanglijie.service.impl;

import wanglijie.dao.RoomDao;
import wanglijie.model.Room;
import wanglijie.service.RoomService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class RoomServiceImpl implements RoomService {
//    public List<Room> selectAvaiableRooms(String roomStatus, String roomType, String roomPriceStart, String roomPriceEnd, int page, int limit) throws SQLException {
//        return new RoomDao().selectAvaiableRooms(roomStatus,roomType,roomPriceStart,roomPriceEnd,page,limit);
//    }

    public List<Room> selectAvaiableRooms(String[] roomStatus, String[] roomType, String roomPriceStart, String roomPriceEnd, int page, int limit) throws SQLException {
        return new RoomDao().selectAvaiableRooms(roomStatus,roomType,roomPriceStart,roomPriceEnd,page,limit);
    }

    public void insertRoom(Room room) throws SQLException {
         new RoomDao().insertOrUpdate(room);
    }


}
