package wanglijie.util;

/**
 * Created by $Jason.Zhang on 12/29/15.
 */
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DaoUtil {
    private static DataSource dataSource;
    private DaoUtil(){}

    public static QueryRunner getQueryRunner(){
        if(DaoUtil.dataSource==null){
            String databaseName = "wanglijie";
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();
            dbcpDataSource.setUrl("jdbc:mysql://localhost:3306/"+databaseName+"?characterEncoding=UTF-8");
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setUsername("root");
            dbcpDataSource.setPassword("root");
            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setMaxActive(100);
            dbcpDataSource.setMaxIdle(30);
            dbcpDataSource.setMaxWait(500);
            DaoUtil.dataSource = (DataSource)dbcpDataSource;
            System.out.println("Initialize dbcp...");
        }
        return new QueryRunner(DaoUtil.dataSource);
    }
}
