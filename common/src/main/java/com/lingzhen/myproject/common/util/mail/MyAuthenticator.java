package com.lingzhen.myproject.common.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 发送邮件验证对象（用户名、密码）
 * @date 2020-08-06
 * @Author lingz
 */
public class MyAuthenticator extends Authenticator {

    String userName;
    String password;

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }

}
