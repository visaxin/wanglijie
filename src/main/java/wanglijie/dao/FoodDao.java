package wanglijie.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import wanglijie.Constant;
import wanglijie.model.Food;
import wanglijie.util.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Jason.Zhang on 1/4/16.
 */
public class FoodDao {
    QueryRunner queryRunner = DaoUtil.getQueryRunner();
    ResultSetHandler<Food> foodBeanHandler = new BeanHandler<Food>(Food.class);

    public void insertOrUpdate(Food food) throws SQLException {
        String sql = "";
        if (select(food.getId()) != null) {
            sql = "update food set foodName = ?,foodPrice=?,foodLeft=?,realOutput=? where id = ?";
            queryRunner.update(sql,food.getFoodName(),food.getFoodPrice(),food.getFoodLeft(),food.getRealOutput());
        } else {
            sql = "insert into food(foodName,foodPrice,foodLeft,realOutput) values(?,?,?,?)";
            queryRunner.update(sql,food.getFoodName(),food.getFoodPrice(),food.getFoodLeft(),food.getRealOutput());
        }
        System.out.println("Insert Or Update:" + sql);
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from food where id = ?";
        queryRunner.update(sql,id);
    }

    public Food select(int id) throws SQLException {
        String sql = "select * from food where id = ?";
        return queryRunner.query(sql,foodBeanHandler,id);
    }
    public List<Food> allFoods() throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Food>> result = new BeanListHandler<Food>(Food.class);
        String sql = "select * from food";
        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);
    }

    public List<Food>  selectAvailableFoods(String foodStatus,String foodType,String foodPriceStart,String foodPriceEnd,int page,int limit) throws SQLException {
        List<Object> arr = new ArrayList<Object>();
        ResultSetHandler<List<Food>> result = new BeanListHandler<Food>(Food.class);
        String sql = "select * from food where foodStatus=?";
        arr.add(foodStatus);
        if (foodType != null) {
            sql += " and foodType=?";
            arr.add(foodType);
        }

        if(foodPriceStart!=null && foodPriceEnd !=null){
            sql += " and foodPrice between ? and ?";
            arr.add(foodPriceStart);
            arr.add(foodPriceEnd);
        }
        if(limit > Constant.MAX_LIMIT){
            limit = Constant.MAX_LIMIT;
        }
        sql+=" limit ?,?";
        arr.add(page*limit);
        arr.add(limit);
        System.out.println("Searching sql: " +sql);
        Object[] params = arr.toArray();
        return queryRunner.query(sql,result,params);
    }

    public Food selectFoodByFoodId(int foodId) throws SQLException {
        String sql = "select * from food where foodId = ?";
        return queryRunner.query(sql,foodBeanHandler,foodId);
    }

}
