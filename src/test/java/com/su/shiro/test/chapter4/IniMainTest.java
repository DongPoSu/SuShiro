package com.su.shiro.test.chapter4;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter4
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/24
 */
public class IniMainTest {


    @Test
    public void test() {

        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:chapter4/shiro-config-main.ini");

        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();

        //将SecurityManager设置到SecurityUtils 方便全局使用
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());



    }
}
