package com.su.shiro.chapter6.sevice;

import com.su.shiro.chapter6.dao.PermissionDao;
import com.su.shiro.chapter6.dao.PermissionDaoImpl;
import com.su.shiro.chapter6.entity.Permission;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.sevice
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/31
 */
public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
