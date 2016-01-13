package wanglijie.servlet.room;

import com.google.gson.Gson;
import wanglijie.Constant;
import wanglijie.model.Room;
import wanglijie.service.impl.RoomServiceImpl;
import wanglijie.util.ResultMap;
import wanglijie.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
        String roomType = StringUtil.decode(request.getParameter("roomType"));
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = StringUtil.decode(request.getParameter("roomStatus"));


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

    public RoomServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");


        String roomStatus = StringUtil.decode(request.getParameter("roomStatus"));
        String roomType = StringUtil.decode(request.getParameter("roomType"));

        String roomPriceStart = request.getParameter("roomPriceStart");
        String roomPriceEnd = request.getParameter("roomPriceEnd");


        int page;
        int limit;
        if (request.getParameter("page") == null && request.getParameter("limit") == null) {
            page = 0;
            limit = 10;
        } else {
            page = Integer.valueOf(request.getParameter("page"));
            limit = Integer.valueOf(request.getParameter("limit"));
        }


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
