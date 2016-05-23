package com.su.shiro.test.chapter2;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by suzheng on 2016/5/19.
 */
public class LoginLogoutTest {
    private static final Logger LOG = LoggerFactory.getLogger(LoginLogoutTest.class.getName());

    @Test
    public void testHelloWorld() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        subject.logout();
    }

    @Test
    public void testCustomRealm() {
        // 1.获取SecurityManager工厂，初始化ini配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        // 2.绑定SecurityManager到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 3.获取主体，及创建token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "546");

        try {
            // 4.登录验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            LOG.info("账户不存在", e);
        } catch (IncorrectCredentialsException e) {
            LOG.info("密码不正确", e);
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testMultiCustomMutilRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter2/shiro-multi-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            // 4.登录验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            LOG.info("账户不存在", e);
        } catch (IncorrectCredentialsException e) {
            LOG.info("密码不正确", e);
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @Test
    public void testJdbcRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            // 4.登录验证
            subject.login(token);
        } catch (UnknownAccountException e) {
            LOG.info("账户不存在", e);
        } catch (IncorrectCredentialsException e) {
            LOG.info("密码不正确", e);
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}
