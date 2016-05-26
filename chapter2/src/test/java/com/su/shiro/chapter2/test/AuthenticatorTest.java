package com.su.shiro.chapter2.test;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/23
 */
public class AuthenticatorTest {
    @Test
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:chapter2/shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection collection = subject.getPrincipals();
        Assert.assertEquals(2, collection.asList().size());
    }

    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess() {
        login("classpath:chapter2/shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了第一个Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void testAtLeastTwoStrategyWithSuccess() {
        login("classpath:chapter2/shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test
    public void testOnlyOneStrategyWithSuccess() {
        login("classpath:chapter2/shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，因为myRealm1和myRealm4返回的身份一样所以输出时只返回一个
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:chapter2/shiro-authenticator-all-fail.ini");
    }

    private void login(String configFile) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
    }

}
