package com.lingzhen.myproject.common.util.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送Util
 * createTime:2020-08-06
 * @Author lingz
 */
public class SimpleMailSender {

    public boolean sendMail(MailSender mailSender) throws Exception{

        Properties pro = mailSender.getProperties();
        Authenticator myAuthenticator = mailSender.getAuthenticator();
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getDefaultInstance(pro,myAuthenticator);
        // 根据session创建一个邮件消息
        Message message = new MimeMessage(session);

        // 设置邮件发送者地址
        message.setFrom(mailSender.getFrom());
        // 设置邮件主题
        message.setSubject(mailSender.getSubject());
        // 设置邮件内容
        if (mailSender.getSendType() == 0) {
            //文本发送发送
            message.setText(mailSender.getContent());
        } else {
            //Html格式发送
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailSender.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            message.setContent(mainPart);
        }
        // 设置邮件发送时间
        message.setSentDate(new Date());
        // 创建邮件的接收者地址，并设置到邮件消息中
        // Message.RecipientType.TO属性表示接收者的类型为TO
        String[] arrTO = mailSender.getToAddress().split(",");
        Address[] TOs = new Address[arrTO.length];
        for(int i = 0;i < arrTO.length;i++){
            TOs[i] = new InternetAddress(arrTO[i]);
        }
        message.setRecipients(Message.RecipientType.TO,TOs);
        // 设置抄送者
        if(null != mailSender.getCC() && mailSender.getCC().length() > 0){
            String[] arrCC = mailSender.getCC().split(",");
            Address[] CCs = new Address[arrCC.length];
            for(int i = 0;i < arrCC.length;i++){
                CCs[i] = new InternetAddress(arrCC[i]);
            }
            message.setRecipients(Message.RecipientType.CC, CCs);
        }
        // 设置秘送
        if(null != mailSender.getBCC() && mailSender.getBCC().length() > 0){
            String[] arrBCC = mailSender.getBCC().split(",");
            Address[] BCCs = new Address[arrBCC.length];
            for(int i = 0;i < arrBCC.length;i++){
                BCCs[i] = new InternetAddress(arrBCC[i]);
            }
            message.setRecipients(Message.RecipientType.BCC, BCCs);
        }
        // 发送邮件
        Transport.send(message);

        return true;
    }

}
