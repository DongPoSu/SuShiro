package com.su.shiro.chapter6.dao;


import com.su.shiro.chapter6.entity.Permission;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.dao
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/26
 */
public interface PermissionDao {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);

}
