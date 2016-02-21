package wanglijie.model;

import java.sql.Timestamp;

/**
 * Created by $Jason.Zhang on 1/10/16.
 */
public class PosProcess {

    private int id;
    private int posId;
    int roomNumber;
    double roomPrice;
    String roomStatus;
    double realIncome;
    Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(double realIncome) {
        this.realIncome = realIncome;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
