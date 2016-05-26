package com.su.shiro.chapter6.test.chapter3.test.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter3.permission
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/24
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if("role1".equals(roleString)) {
            return Arrays.asList((Permission)new WildcardPermission("menu:*"));
        }
        return null;
    }
}
