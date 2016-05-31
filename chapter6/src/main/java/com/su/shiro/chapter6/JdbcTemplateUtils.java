package com.su.shiro.chapter6;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

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
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.driver");
//        druidDataSource.setUrl("jdbc:mysql://120.76.43.202:3306/test");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("Aa12346");
        return new JdbcTemplate(druidDataSource);
    }

}
