package control.sun.serviceImpl;

import control.sun.domain.User;
import control.sun.mail.SendJunkMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;


@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")   // 将application.properties配置文件中的配置设置到属性中
    private String from;

    public static final Logger logger = LogManager.getLogger(SendJunkMailServiceImpl.class);

    @Override
    public boolean sendJunkMail(List<User> userList) {

        try {

            if (userList == null || userList.size() <= 0) {

                return Boolean.FALSE;

            }

            for (User user : userList) {

                MimeMessage mimeMessage = this.mailSender.createMimeMessage();

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                // 发送方
                message.setFrom(from);

                // 邮件主题
                message.setSubject("have fun with BoBo!");

                // 接收方
                message.setTo("403883229***@qq.com");

                // 内容
                message.setText(user.getUserName() + "：I only do three things every day: eat, sleep, play BoBo. hhhhhh!!!\n");

                // 发送邮件
                this.mailSender.send(mimeMessage);

            }

        } catch (Exception ex) {

            logger.error("sendJunMail error and user = %s", userList, ex);

            return Boolean.FALSE;

        }

        return Boolean.TRUE;

    }

}



