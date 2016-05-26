package com.su.shiro.chapter6.sevice;

import com.su.shiro.chapter6.entity.Permission;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.sevice
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/26
 */
public interface PermissionService {
    Permission createPermission(Permission permission);
    void deletePermission(Long permissionId);

}
