package wanglijie.servlet.user;

import com.google.gson.Gson;
import wanglijie.service.UserService;
import wanglijie.service.impl.UserServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by $Jason.Zhang on 12/29/15.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Gson gson = new Gson();

        if (session.getAttribute("userId") != null) {
            System.out.println("用户已经登陆!");
            response.getWriter().write(gson.toJson(
                    new ResultMap()
                            .result(307, "用户已经登陆!", String.valueOf(session.getAttribute("userId")))));
            return;
        }
        String userId = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        try {
            if (userService.validUser(userId, password)) {
                System.out.println("用户验证通过!");
                session.setAttribute("userId", userId);
                response.getWriter().write(gson.toJson(new ResultMap().result(200,"登陆成功!",userId)));
            } else {
                System.out.println("用户验证验证失败!");
                response.getWriter().write(gson.toJson(new ResultMap().result(401,"登陆失败!",userId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(gson.toJson(new ResultMap().result(503,"登陆失败:"+e.getSQLState(),"")));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
