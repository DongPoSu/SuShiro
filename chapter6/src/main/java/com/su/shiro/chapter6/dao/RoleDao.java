package com.su.shiro.chapter6.dao;

import com.su.shiro.chapter6.entity.Role;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.dao
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/31
 */
public interface RoleDao {
   Role createRole(Role role);
   void deleteRole(Long roleId);

   void correlationPermissions(Long roleId, Long... permissionIds);
   void unCorrelationPermissions(Long roleId, Long... permissionIds);
}
