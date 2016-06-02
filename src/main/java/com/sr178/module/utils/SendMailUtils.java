package com.sr178.module.utils;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SendMailUtils {
    
    public static void sendMail(String host,int port,String userName,String password,String nickerName,String toAddress,String mailTitle,String mailContent) throws MessagingException{
    	 // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);   
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置, 
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号
        props.put("mail.user", userName);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", password);
        
        String nick="";  
        try {  
            nick=javax.mail.internet.MimeUtility.encodeText(nickerName);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }   

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form=null;
			form = new InternetAddress(nick+"<"+userName+">");
			 message.setFrom(form);
		        // 设置收件人
		     InternetAddress to = new InternetAddress(toAddress);
		     message.setRecipient(MimeMessage.RecipientType.TO, to);
		        // 设置邮件标题
		     message.setSubject(mailTitle);
		        // 设置邮件的内容体
		     message.setContent(mailContent, "text/html;charset=UTF-8");
		        // 发送邮件
		     Transport.send(message);
		
       
    }
}