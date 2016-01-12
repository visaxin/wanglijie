package wanglijie.servlet.user;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import wanglijie.model.User;
import wanglijie.service.UserService;
import wanglijie.service.impl.UserServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/3/16.
 */
public class Registservlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        Gson gson = new Gson();

        String idNumber = request.getParameter("idNumber");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        String age = request.getParameter("age");

        User user = new User(idNumber,name,email,password,gender,role,age);

        UserService userService = new UserServiceImpl();
        try {
            userService.updateOrInsertUser(user);
        } catch (SQLException e) {
            response.setStatus(401);
            response.getWriter().write(gson.toJson(new ResultMap().resultJson(503,"注册失败","")));
            e.printStackTrace();
        }
    }
}
