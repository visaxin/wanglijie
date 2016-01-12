package wanglijie.service;

import wanglijie.model.Cashier;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public interface CashierService {
    public void cash(Cashier cashier) throws SQLException;
}
