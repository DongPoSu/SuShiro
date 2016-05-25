package com.su.shiro.test.chapter5;

import com.su.shiro.test.BaseTest;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.junit.Test;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.test.Chapter5
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/25
 */
public class PasswordTest extends BaseTest {

    @Test
    public void testPasswordServiceWithMyRealm() {
        login("classpath:chapter5/shiro-passwordservice.ini", "wu", "123");
    }

    @Test
    public void testPasswordServiceWithJdbcRealm() {
        login("classpath:chapter5/shiro-jdbc-passwordservice.ini", "wu", "123");
    }

    @Test
    public void testHashedCredentialsMatcherWithMyRealm2() {
        //使用testGeneratePassword生成的散列密码
        login("classpath:chapter5/shiro-hashedCredentialsMatcher.ini", "liu", "123");
    }

    @Test
    public void testHashedCredentialsMatcherWithJdbcRealm() {
        BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
        //使用testGeneratePassword生成的散列密码
        login("classpath:chapter5/shiro-jdbc-hashedCredentialsMatcher.ini", "liu", "123");
    }


    private class EnumConverter extends AbstractConverter {
        protected String convertToString(final Object value) throws Throwable {
            return ((Enum) value).name();
        }
        protected Object convertToType(final Class type, final Object value) throws Throwable {
            return Enum.valueOf(type, value.toString());
        }

        protected Class getDefaultType() {
            return null;
        }

    }

    @Test(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for(int i = 1; i <= 5; i++) {
            try {
                login("classpath:chapter5/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        login("classpath:chapter5/shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
    }


}
