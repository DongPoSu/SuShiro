package com.su.shiro.chapter2.test.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/23
 */
public class MyRealm2 implements Realm {
    public String getName() {
        return "myRealm2";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        String passWord = new String((char[])token.getCredentials());

        if(!"suzheng".equals(userName)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!"123".equals(passWord)) {
            throw  new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(userName, passWord, getName());
    }
}
