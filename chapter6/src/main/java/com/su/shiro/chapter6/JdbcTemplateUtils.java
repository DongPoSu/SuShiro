package com.su.shiro.chapter6;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/26
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate jdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = createJdbcTemplate();
        }
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate() {
//        DriverManagerDataSource dataSource = new SingleConnectionDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://120.76.43.202:3306/test");
////        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
//        dataSource.setUsername("root");
//        dataSource.setPassword("Aa12346");

//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://120.76.43.202:3306/test");
//        dataSource.setMinIdle(1);
//        dataSource.setUsername("root");
//        dataSource.setPassword("Aa12346");
//        dataSource.setConnectionErrorRetryAttempts(2);
//        dataSource.setInitialSize(1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME,"com.mysql.jdbc.Driver");
        map.put(DruidDataSourceFactory.PROP_URL,"jdbc:mysql://127.0.0.1:3306/test");
        map.put(DruidDataSourceFactory.PROP_PASSWORD,"Aa123456");
        map.put(DruidDataSourceFactory.PROP_USERNAME, "root");
        map.put(DruidDataSourceFactory.PROP_MAXIDLE, "5");
        map.put(DruidDataSourceFactory.PROP_MINIDLE, "1");
        map.put(DruidDataSourceFactory.PROP_INITIALSIZE, "1");
        map.put(DruidDataSourceFactory.PROP_INIT, "true");

        DruidDataSource dataSource = null;
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JdbcTemplate(dataSource);
    }
}
