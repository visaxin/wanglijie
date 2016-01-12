package wanglijie.model;

import java.sql.Timestamp;

/**
 * Created by $Jason.Zhang on 1/5/16.
 */
public class Cashier {
    int id;
    int customer_id;
    double cashPledge;
    double price;
    int days;
    Timestamp in_day;
    Timestamp out_day;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(double cashPledge) {
        this.cashPledge = cashPledge;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Timestamp getIn_day() {
        return in_day;
    }

    public void setIn_day(Timestamp in_day) {
        this.in_day = in_day;
    }

    public Timestamp getOut_day() {
        return out_day;
    }

    public void setOut_day(Timestamp out_day) {
        this.out_day = out_day;
    }
}
