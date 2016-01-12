package wanglijie.service;

import wanglijie.model.Room;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public interface RoomService {

    public List<Room> selectAvaiableRooms(String[] roomStatus, String[] roomType, String roomPriceStart, String roomPriceEnd, int page, int limit) throws SQLException;

    public void insertRoom(Room room) throws SQLException;
}
