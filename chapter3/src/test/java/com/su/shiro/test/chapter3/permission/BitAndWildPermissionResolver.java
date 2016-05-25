package com.su.shiro.test.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter3.permission
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/24
 */
public class BitAndWildPermissionResolver implements PermissionResolver{
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
