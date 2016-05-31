package com.su.shiro.chapter6.dao;

import com.su.shiro.chapter6.JdbcTemplateUtils;
import com.su.shiro.chapter6.entity.Permission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.dao
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/31
 */
public class PermissionDaoImpl implements PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    public Permission createPermission(final Permission permission) {
        final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});
                preparedStatement.setString(1, permission.getPermission());
                preparedStatement.setString(2, permission.getDescription());
                preparedStatement.setBoolean(3, permission.getAvailable());
                return preparedStatement;
            }
        });
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    public void deletePermission(Long permissionId) {
        //首先把与permission关联的相关表的数据删掉
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, permissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, permissionId);
    }
}
