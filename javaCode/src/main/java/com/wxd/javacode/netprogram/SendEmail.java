package com.wxd.javacode.netprogram;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

public class SendEmail {
    public static void main(String[] args) throws Exception {
        // 服务器地址:
        String smtp = "smtp.qq.com";
        // 登录用户名:
        String username = "1337877200@qq.com";
        // 登录口令:
        String password = "tyualidonvbfgghh";
        // 连接到SMTP服务器587端口:
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", "587"); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密
        // 获取Session实例:
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // 设置debug模式便于调试:
        session.setDebug(true);
        MimeMessage message = getMimeMessage(session, "1337877200@qq.com", "409541523@qq.com", "1337877200@qq.com", "409541523@qq.com");
        // 发送:
        Transport.send(message);
    }

    private static void getMimeTextMessage(Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 设置发送方地址:
        message.setFrom(new InternetAddress("1337877200@qq.com"));
        // 设置接收方地址:
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("409541523@qq.com"));
        // 设置邮件主题:
        message.setSubject("Hello", "UTF-8");
        // 设置邮件正文:
        message.setText("Hi WeiHao...", "UTF-8");
        /*message.setText("<html>\n" +
                "\n" +
                "<head>\n" +
                "<title>我的第一个 HTML 页面</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<p>body 元素的内容会显示在浏览器中。</p>\n" +
                "<p>title 元素的内容会显示在浏览器的标题栏中。</p>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n", "UTF-8", "html");*/
    }

    /**
     * MimeMessage是Message的子类，Message是所有电子邮件的超类；
     * 标准的Java Mail API中有一个Message的子类：MimeMessage，它可用于电子邮件和Usenet新闻消息。除此之外，其他厂商可以自由扩展Message来满足自身需求
     *
     * @param session   Session:对象
     * @param send      :邮件的发送者
     * @param receive   ：邮件的接收者
     * @param cReceive  : 邮件的抄送这
     * @param bcReceive :邮件的密送者
     * @return
     * @throws Exception
     */
    public static MimeMessage getMimeMessage(Session session, String send, String receive, String cReceive, String bcReceive) throws Exception {
        //1.创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //2.设置发件人地址
        msg.setFrom(new InternetAddress(send));
        /**
         * 3.设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receive));
        msg.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cReceive));
        msg.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress(bcReceive));
        //4.设置邮件主题
        msg.setSubject("邮件标题(包含图片和附件)", "UTF-8");

        // 5. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        // 读取本地文件
        DataHandler dataHandler = new DataHandler(new FileDataSource("app/src/main/res/drawable-xxhdpi/apple.jpg"));
        // 将图片数据添加到"节点"
        image.setDataHandler(dataHandler);
        // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）,若需要增加好多图片则用ID来区分
        image.setContentID("mailPic");

        // 6. 创建文本"节点",MimeBodyPart表示的是一个单节点
        MimeBodyPart text = new MimeBodyPart();
        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("这是一个苹果<br/><img src='cid:mailPic'/></a>", "text/html;charset=UTF-8");

        // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）, MimeMultipart表示的是一个符合节点
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        // 关联关系
        mm_text_image.setSubType("related");

        // 8. 将 文本+图片 的混合"节点"封装成一个普通"节点"
        // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        // 上面的 mailTestPic 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 9. 创建附件"节点"
        MimeBodyPart attachment = new MimeBodyPart();
        // 读取本地文件
        DataHandler dataHandler2 = new DataHandler(new FileDataSource("app/src/main/res/drawable-xxhdpi/apple.jpg"));
        // 将附件数据添加到"节点"
        attachment.setDataHandler(dataHandler2);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText(dataHandler2.getName()));

        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        // 如果有多个附件，可以创建多个多次添加
        mm.addBodyPart(attachment);
        // 混合关系
        mm.setSubType("mixed");

        // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        msg.setContent(mm);
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        return msg;
    }
}
