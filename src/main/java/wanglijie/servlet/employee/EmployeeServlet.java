package wanglijie.servlet.employee;

import com.google.gson.Gson;
import wanglijie.model.Employee;
import wanglijie.model.User;
import wanglijie.service.EmployeeService;
import wanglijie.service.UserService;
import wanglijie.service.impl.EmployeeServiceImpl;
import wanglijie.service.impl.UserServiceImpl;
import wanglijie.util.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/13/16.
 */
public class EmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        Gson gson = new Gson();

        int idNumber = Integer.valueOf(request.getParameter("idNumber"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        double salary = Double.valueOf(request.getParameter("salary"));
        String gender = request.getParameter("gender");
        String role = request.getParameter("role");
        int age = Integer.valueOf(request.getParameter("age"));

        Employee employee = new Employee(idNumber,name,email,gender,role,age,salary);

        EmployeeService employeeService = new EmployeeServiceImpl();
        try {
                employeeService.addNewEmployee(employee);
        } catch (SQLException e) {
            response.setStatus(503);
            response.getWriter().write(gson.toJson(new ResultMap().resultJson(503,"增加失败","")));
            e.printStackTrace();
        }
        response.setStatus(200);
        response.getWriter().write(gson.toJson(new ResultMap().resultJson(200,"增加","")));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        Gson gson = new Gson();

        int idNumber = Integer.valueOf(request.getParameter("idNumber"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");


    }
}
