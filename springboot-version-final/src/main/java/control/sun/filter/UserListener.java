package control.sun.filter;

import control.sun.domain.User;
import control.sun.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class UserListener implements ServletContextListener {

    // 日志输出
    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserService userService;


    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // 查询数据库中所有的用户
        List<User> userList = userService.findAll();

        // 清除缓存中的用户数据
        redisTemplate.delete(ALL_USER);

        // 将数据存放到redis缓存中
        redisTemplate.opsForList().leftPushAll(ALL_USER, userList);

        // 在实际项目中要注释掉
        List<User> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);

//        System.out.println("缓存中目前的用户人数为: " + queryUserList.size() + "人");
//
//        System.out.println("ServletContext上下文初始化");

        logger.info("缓存中目前的用户人数为: " + queryUserList.size() + "人");
        logger.info("ServletContext上下文初始化");

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

//        System.out.println("ServletContext销毁");
        logger.info("ServletContext销毁");

    }

}
