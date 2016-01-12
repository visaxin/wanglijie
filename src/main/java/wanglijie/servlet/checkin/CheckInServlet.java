package wanglijie.servlet.checkin;

import com.google.gson.Gson;
import wanglijie.model.CheckIn;
import wanglijie.service.impl.CheckInServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public class CheckInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.valueOf(request.getParameter("roomId"));
        int customerId = Integer.valueOf(request.getParameter("customerId"));
        long customerNumber = Long.valueOf(request.getParameter("customerNumber"));

        CheckIn checkIn = new CheckIn();
        checkIn.setCustomerId(customerId);
        checkIn.setRoomId(roomId);
        checkIn.setCustomerNumber(customerNumber);

        Gson gson = new Gson();
        try {
            new CheckInServiceImpl().checkIn(checkIn);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(gson.toJson(new ResultMap().resultJson(503, "Unknown", e.getSQLState())));
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
