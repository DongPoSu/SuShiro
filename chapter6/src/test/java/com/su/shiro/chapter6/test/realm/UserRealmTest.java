package com.su.shiro.chapter6.test.realm;

import com.su.shiro.chapter6.test.BaseTest;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by suzheng on 2016/5/31.
 */
public class UserRealmTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
        login("classpath:shiro.ini", u1.getUsername(), password);
        Assert.assertTrue(subject().isAuthenticated());
    }
}
