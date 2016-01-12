package wanglijie.servlet.room;

import com.google.gson.Gson;
import wanglijie.model.Room;
import wanglijie.service.impl.RoomServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class RoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        String roomId = request.getParameter("roomId");
        String roomType = request.getParameter("roomType");
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = request.getParameter("roomStatus");

        Room room = new Room(Integer.valueOf(roomId), roomType, Double.valueOf(roomPrice), roomStatus);
        Gson gson = new Gson();
        try {
            new RoomServiceImpl().insertRoom(room);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(503);
            response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", e.getSQLState())));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        String[] roomStatus = request.getParameterValues("roomStatus");
        String[] roomType = request.getParameterValues("roomType");
        String roomPriceStart = request.getParameter("roomPriceStart");
        String roomPriceEnd = request.getParameter("roomPriceEnd");
        int page = Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));

        Gson gson = new Gson();
        List<Room> result = null;
        try {
            result = new RoomServiceImpl().selectAvaiableRooms(roomStatus, roomType, roomPriceStart, roomPriceEnd, page, limit);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", e.getSQLState())));
        }

        response.getWriter().write(gson.toJson(result));

    }
}