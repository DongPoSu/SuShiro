package com.su.shiro.chapter6.sevice;

import com.su.shiro.chapter6.dao.RoleDao;
import com.su.shiro.chapter6.dao.RoleDaoImpl;
import com.su.shiro.chapter6.entity.Role;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.sevice
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/31
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao = new RoleDaoImpl();


    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId, permissionIds);
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void unCorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.unCorrelationPermissions(roleId, permissionIds);
    }
}
