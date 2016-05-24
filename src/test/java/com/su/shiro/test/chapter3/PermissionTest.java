package com.su.shiro.test.chapter3;

import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Test;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter3
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/23
 */
public class PermissionTest extends BaseTest {

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:chapter3/shiro-permission.ini", "su", "123");
        subject().checkPermission("user:create");
        subject().checkPermission("user:update");
        subject().checkPermissions("user:delete", "user:create");
        subject().checkPermission("user:view");
    }

    @Test
    public void testisPermitted() {
        login("classpath:chapter3/shiro-permission.ini", "su", "123");
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermitted("user:update"));
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:create"));
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test
    public void testWildcardPermission1() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission2() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        subject().checkPermissions("system:user:create,delete,update:view");

        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    @Test
    public void testWildcardPermission3() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        Assert.assertTrue(subject().isPermitted("system:user:view"));
        Assert.assertTrue(subject().isPermitted("user:view"));
    }

    @Test
    public void testWildcardPermission4() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view:1");

        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        subject().checkPermissions("user:auth:1", "user:auth:2");
    }

    @Test
    public void testWildcardPermission5() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        subject().checkPermissions("menu:view:1");

        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

    }

    @Test
    public void testWildcardPermission6() {
        login("classpath:chapter3/shiro-permission.ini", "li", "123");
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));

    }

}
