package xyz.moreco.component.message.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author zhangwei
 * date 2018/11/24
 * time 18:54
 */
@Slf4j
@Component
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     *
     * @param from    发送人
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleEmail(String from, String[] to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();//创建简单邮件消息
        message.setFrom(from);//设置发送人
        message.setTo(to);//设置收件人
        message.setSubject(subject);//设置主题
        message.setText(content);//设置内容
        mailSender.send(message);//执行发送邮件
    }

    /**
     * 发送html邮件
     *
     * @param from    发件人
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlEmail(String from, String[] to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     *
     * @param from               发件人
     * @param to                 收件人
     * @param subject            主题
     * @param content            内容
     * @param attachmentFilename 附件名
     * @param inputStreamSource  附件内容
     * @throws MessagingException
     */
    public void sendAttachmentsEmail(String from, String[] to, String subject, String content, String attachmentFilename, InputStreamSource inputStreamSource) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);// true表示这个邮件是有附件的
        helper.addAttachment(attachmentFilename, inputStreamSource);//添加附件
        mailSender.send(message);
    }

}
