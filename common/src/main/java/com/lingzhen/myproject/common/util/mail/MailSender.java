package com.lingzhen.myproject.common.util.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

/**
 * 邮件信息对象
 * @date 2020-08-06
 * @Author lingz
 */
public class MailSender {
    // 发送邮件的服务器IP
    private String host;
    // 发送邮件的服务器端口
    private String port = "25";
    // 邮件发送者的用户名
    private String userName;
    // 邮件发送者的密码
    private String password;
    // 邮件发送者的地址
    private String fromAddress;
    // 邮件接收者的地址
    private String toAddress;
    // 是否需要身份验证
    private boolean auth = false;
    //是否加密
    private boolean isSSL = false;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件发送方式（0文本，1网页）
    private int sendType = 0;
    // 抄送地址
    private String CC;
    // 秘送地址
    private String BCC;

    //获取邮件属性
    public Properties getProperties() throws Exception{
        Properties prop = new Properties();
        prop.put("mail.smtp.host", this.host);
        prop.put("mail.smtp.port", this.port);
        prop.put("mail.smtp.auth", this.auth ? "true" : "false");

        //发送方式(默认SMTP)
        //SSL安全验证
        if(this.isSSL){
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "false");
            prop.put("mail.smtp.socketFactory.port", this.port);
            prop.put("mail.smtp.port", this.port);

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.socketFactory", sf);
        }

        return prop;
    }
    //获取邮件验证对象
    public Authenticator getAuthenticator() {
        return new MyAuthenticator(this.userName,this.password);
    }
    //获取发送者地址对象
    public Address getFrom() throws Exception{
        return new InternetAddress(this.fromAddress);
    }
    //获取默认的发送者邮箱设置
    public static MailSender getDefaultInstance() {
        MailSender mailSender = new MailSender();
        mailSender.setHost("Smtp.qq.com");
        mailSender.setPort("25");
        mailSender.setUserName("2121517232@qq.com");
        mailSender.setFromAddress("2121517232@qq.com");
        mailSender.setPassword("wytuxbxaugwoieef");

        return mailSender;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean getAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsSSL() {
        return isSSL;
    }

    public boolean isSSL() {
        return isSSL;
    }

    public void setSSL(boolean SSL) {
        isSSL = SSL;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getBCC() {
        return BCC;
    }

    public void setBCC(String BCC) {
        this.BCC = BCC;
    }
}
