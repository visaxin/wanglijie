package wanglijie.servlet.Cashier;

import com.google.gson.Gson;
import wanglijie.model.Cashier;
import wanglijie.service.CashierService;
import wanglijie.service.impl.CashierServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public class CashierServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        int customerId = Integer.valueOf(request.getParameter("customerId"));
        double cashPledge = Double.valueOf(request.getParameter("cashPledage"));
        double  price  = Double.valueOf(request.getParameter("price"));
        int days = Integer.valueOf(request.getParameter("days"));
        Timestamp dayIn = new Timestamp(Long.valueOf(request.getParameter("inDay"))*1000);
        Timestamp dayOut = new Timestamp(Long.valueOf(request.getParameter("outDay"))*1000);

        Cashier cashier = new Cashier();
        cashier.setCustomer_id(customerId);
        cashier.setCashPledge(cashPledge);
        cashier.setPrice(price);
        cashier.setDays(days);
        cashier.setIn_day(dayIn);
        cashier.setOut_day(dayOut);

        Gson gson = new Gson();
        try {
            new CashierServiceImpl().cash(cashier);
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO test if work!
            new ResultMap().responseResult(response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
