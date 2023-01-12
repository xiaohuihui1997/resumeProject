package com.wjz.utils;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtil {

    //邮件服务器地址(SMTP)
    public static String myEmailSMTPHost = "smtp.qq.com";

   //授权码 wbwjshsvpdekdgjb

    /**
     * 发送纯文本邮件
     * @param mailTitle 邮件主题
     * @param content 邮件内容
     * @param sendMail 发件人邮箱地址 2875778074@qq.com
     * @param sendName 发件人姓名 雷超
     * @param receiveMail 收件人 355736353@qq.com
     * @param sendMailPassword 发送人邮箱授权码
     * @throws Exception
     */
    public static void sendEmail(String mailTitle, String content, String sendMail, String sendName,
                                 String receiveMail, String sendMailPassword) throws Exception{
        Properties p = new Properties();
        p.setProperty("mail.transport.protocol", "smtp");
        p.setProperty("mail.smtp.host", myEmailSMTPHost);
        p.setProperty("mail.smtp.localhost", "localhost");//不加的话在linux报错：501 syntax:ehlo hostname
        p.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(p);
        //设置为debug模式，可以查看详细的发送log
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        //收件人(可以增加多个收件人、抄送、密送)
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail,"UTF-8"));

        // 4,抄送人
//        message.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(sendMail));

        //邮件主题
        message.setSubject(mailTitle, "UTF-8");


        //邮件正文
        message.setContent(content, "text/html;charset=UTF-8");

        //设置显示的发件时间
        message.setSentDate(new Date());

        //保存前面的设置
        message.saveChanges();

        //根据session获取邮件传输对象
        Transport transport = session.getTransport();
        //连接邮箱服务器
        transport.connect(sendMail, sendMailPassword);
        //发送邮件到所有的收件地址
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }


    //fileUrl 文件路径
    //和上边方法基本相同，只不过可以添加附件发送邮件
    public static void sendEmailWithFile(String mailTitle, String content, String sendMail, String sendName,
                                         List<String> receiveMailList, String sendMailPassword,String fileUrl) throws Exception{
        Properties p = new Properties();
        p.setProperty("mail.transport.protocol", "smtp");
        p.setProperty("mail.smtp.host", myEmailSMTPHost);
        //p.setProperty("mail.smtp.localhost", "localhost");//不加的话在linux报错：501 syntax:ehlo hostname
        p.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(p);
        //设置为debug模式，可以查看详细的发送log
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(sendMail, sendName, "UTF-8"));

        //收件人
        for(String receiveAddress : receiveMailList){
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveAddress, receiveAddress, "UTF-8"));
        }

        //邮件主题
        message.setSubject(mailTitle, "UTF-8");

        //向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        MimeMultipart multipart = new MimeMultipart();
        //设置邮件的文本内容
        MimeBodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        //添加附件
        MimeBodyPart filePart = new MimeBodyPart();
        DataSource source = new FileDataSource(fileUrl + File.separator + "附件.xlsx");
        //添加附件的内容
        filePart.setDataHandler(new DataHandler(source));
        //添加附件的标题
        filePart.setFileName(MimeUtility.encodeText("附件.xlsx"));
        multipart.addBodyPart(filePart);
        multipart.setSubType("mixed");
        //将multipart对象放到message中
        message.setContent(multipart);

        //设置显示的发件时间
        message.setSentDate(new Date());

        //保存前面的设置
        message.saveChanges();

        //根据session获取邮件传输对象
        Transport transport = session.getTransport();
        //连接邮箱服务器
        transport.connect(sendMail, sendMailPassword);
        //发送邮件到所有的收件地址
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
