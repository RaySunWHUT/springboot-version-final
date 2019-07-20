package control.sun.quartz;


import control.sun.domain.User;
import control.sun.mail.SendJunkMailService;
import control.sun.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Configuration  // 加此注解的类相当于XML配置文件
@EnableScheduling   // 开启对计划任务的支持
public class SendMailQuartz {

    // 日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService sendJunkMailService;

    @Resource
    private UserService userService;

    // 5秒执行一次
    @Scheduled(cron = "*/5 * *  * * * ")    // 定时任务, cron表达式为执行的时机
    public void reportCurrentByCron() {

        List<User> userList = userService.findAll();

        if (userList == null || userList.size() <= 0) {

            return;

        }

        // 发送邮件
        sendJunkMailService.sendJunkMail(userList);

        logger.info("定时器运行了！！！");

    }

}


