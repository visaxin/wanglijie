package wanglijie.servlet.posSell;

import com.google.gson.Gson;
import wanglijie.dao.PosDao;
import wanglijie.model.PosProcess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by $Jason.Zhang on 2/10/16.
 */
public class PosSellServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        Gson gson = new Gson();
        List<PosProcess> pp = null;

        try {
           pp =  new PosDao().allPosProcesss();
            Map<String,List<PosProcess>> res = new HashMap<String,List<PosProcess>>();
            res.put("data",pp);
            response.getWriter().write(gson.toJson(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
