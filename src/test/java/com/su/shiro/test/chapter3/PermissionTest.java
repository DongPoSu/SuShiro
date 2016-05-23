package com.su.shiro.test.chapter3;

import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
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
}
