package com.su.shiro.test.chapter3.realm;

import com.su.shiro.test.chapter3.permission.BitPermission;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.chapter3.realm
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/24
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        final String username = (String) token.getPrincipal();
        final String password = new String((char[]) token.getCredentials());
        if (!"su".equals(username)) {
            throw new UnknownAccountException("用户名不存在！");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误！");
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }
}
