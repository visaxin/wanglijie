package wanglijie.model;

import java.sql.Timestamp;

/**
 * Created by $Jason.Zhang on 1/10/16.
 */
public class Food {

    private int id;
    String foodName;
    double foodPrice;
    int foodLeft;
    double realOutput;
    Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodLeft() {
        return foodLeft;
    }

    public void setFoodLeft(int foodLeft) {
        this.foodLeft = foodLeft;
    }

    public double getRealOutput() {
        return realOutput;
    }

    public void setRealOutput(double realOutput) {
        this.realOutput = realOutput;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
