package com.su.shiro.chapter4;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter4
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/24
 */
public class ConfigurationCreateTest {
    @Test
    public void testConfiguarationIni() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:chapter4/shiro-config.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
}
