package wanglijie.service.impl;

import wanglijie.dao.CashierDao;
import wanglijie.model.Cashier;
import wanglijie.service.CashierService;

import java.sql.SQLException;

/**
 * Created by $Jason.Zhang on 1/7/16.
 */
public class CashierServiceImpl implements CashierService {
    public void cash(Cashier cashier) throws SQLException {
        new CashierDao().insertOrUpdate(cashier);
    }
}
