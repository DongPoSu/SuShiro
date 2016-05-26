package com.su.shiro.chapter6.sevice;

import com.su.shiro.chapter6.entity.User;

import java.util.Set;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.sevice
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/26
 */
public interface UserService {
    User createUser(User user); //创建账户
    void changePassword(Long userId, String newPassword);//修改密码
    void correlationRoles(Long userId, Long... roleIds); //添加用户-角色关系
    void uncorrelationRoles(Long userId, Long... roleIds);// 移除用户-角色关系
    User findByUsername(String username);// 根据用户名查找用户
    Set<String> findRoles(String username);// 根据用户名查找其角色
    Set<String> findPermissions(String username); //根据用户名查找其权限
}
