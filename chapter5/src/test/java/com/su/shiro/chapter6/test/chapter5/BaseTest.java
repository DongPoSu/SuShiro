package com.su.shiro.chapter6.test.chapter5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.Chapter5
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/25
 */
public abstract class BaseTest {

    @After
    public void teartDown() {
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
