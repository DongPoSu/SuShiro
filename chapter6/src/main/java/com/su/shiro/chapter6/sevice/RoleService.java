package com.su.shiro.chapter6.sevice;

import com.su.shiro.chapter6.entity.Role;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.sevice
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/26
 */
public interface RoleService {
    Role createRole(Role role);
    void deleteRole(Long roleId);
    //添加角色-权限之间关系
    void correlationPermissions(Long roleId, Long... permissionIds);
    //移除角色-权限之间关系
    void unCorrelationPermissions(Long roleId, Long... permissionIds);
}
