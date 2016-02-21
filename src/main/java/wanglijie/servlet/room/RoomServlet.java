package wanglijie.servlet.room;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import wanglijie.Constant;
import wanglijie.dao.RoomDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String roomStatus =request.getParameter("roomStatus");

        System.out.println("save:"+roomType+roomStatus);
        System.out.println("save:"+request.getParameter("roomType")+request.getParameter("roomStatus"));

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

        boolean isSearch = false;
        try{
            isSearch = Boolean.valueOf(request.getParameter("isSearch"));
        }catch (NullPointerException e){
            System.out.println("This is not search request.");
        }
        if(isSearch) {
            String roomStatus = StringUtil.decode(request.getParameter("roomStatus"));
            String roomType = StringUtil.decode(request.getParameter("roomType"));

            String roomPriceStart = request.getParameter("roomPriceStart");
            String roomPriceEnd = request.getParameter("roomPriceEnd");
            System.out.println("Searching ..." + roomStatus + ":"+roomType + ":" + roomPriceStart + roomPriceEnd);


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
                result = new RoomServiceImpl().selectAvailableRooms(roomStatus, roomType, roomPriceStart, roomPriceEnd, page, limit);
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", e.getSQLState())));
            }
            Map<String, List<Room>> res = new HashMap<String, List<Room>>();
            res.put("data", result);
            response.getWriter().write(gson.toJson(res));
        }else{
            Gson gson = new Gson();
            List<Room> result = null;
            try {
                result = new RoomDao().allRooms();
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", e.getSQLState())));
            }
            Map<String,List<Room>> res = new HashMap<String, List<Room>>();
            res.put("data",result);
            response.getWriter().write(gson.toJson(res));
        }

    }
}
