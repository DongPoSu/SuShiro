package com.su.shiro.chapter6.dao;

import com.su.shiro.chapter6.entity.User;

import java.util.Set;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.dao
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/31
 */
public interface UserDao {

    User createUser(User user);
    void updateUser(User user);
    void deleteUser(Long userId);

    void correlationRoles(Long userId, Long... roleIds);
    void uncorrelationRoles(Long userId, Long... roleIds);

    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
