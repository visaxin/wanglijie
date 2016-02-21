package wanglijie.servlet.food;

import com.google.gson.Gson;
import wanglijie.dao.FoodDao;
import wanglijie.model.Food;

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
public class FoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        String foodName = request.getParameter("foodName");
        double foodPrice = Double.valueOf(request.getParameter("foodPrice"));
        int foodLeft = Integer.valueOf(request.getParameter("foodLeft"));
        double realOutput = Double.valueOf(request.getParameter("realOutput"));

        Food food = new Food();
        food.setFoodLeft(foodLeft);
        food.setFoodName(foodName);
        food.setFoodPrice(foodPrice);
        food.setRealOutput(realOutput);

        try {
            new FoodDao().insertOrUpdate(food);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        Gson gson = new Gson();

        List<Food> foods = null;

        try {
            foods = new FoodDao().allFoods();
            Map res =  new HashMap();
            res.put("data",foods);
            response.getWriter().write(gson.toJson(res));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
