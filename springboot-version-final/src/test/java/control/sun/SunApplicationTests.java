package control.sun;

import control.sun.activeMQ.MoodProducer;
import control.sun.domain.Mood;
import control.sun.domain.User;
import control.sun.domain.UserAttachmentRel;
import control.sun.service.MoodService;
import control.sun.service.UserAttachmentRelService;
import control.sun.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SunApplicationTests {

    @Test
    public void contextLoads() {
    }


    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private UserAttachmentRelService userAttachmentRelService;

    @Resource
    private MoodProducer moodProducer;

    @Resource
    private MoodService moodService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;



    @Test
    public void sqlTest() {

        String sql = "select userId, userName, userAccount, password from user";

        List<User> userList = (List<User>) jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int i) throws SQLException {

                User user = new User();

                user.setUserId(rs.getInt("userId"));

                user.setUserName(rs.getString("userName"));

                user.setUserAccount(rs.getString("userAccount"));

                return user;

            }

        });

        System.out.println("query successful: ");

        for (User user : userList) {

            System.out.println("userId: " + user.getUserId() + "  userName：" + user.getUserName() + "  userAccount: " + user.getUserAccount());

        }

    }


    @Test     // problem
    public void testRepository() {

        // 查询所有数据
        List<User> userList = userService.findAll();

        System.out.println("findAll()：" + userList.size());


        // 通过userId查找
        User user0 = userService.findByUserId(1);
        System.out.println("userId = " + user0.getUserId() + "; userName = " + user0.getUserName());


        // 精确查询
        User user1 = userService.findByUserName("Sun");

        System.out.println("findByUserName()：" + user1);

        Assert.isTrue(user1.getUserName().equals("Sun"), "data error!");

        // 模糊查询
        List<User> userList2 = userService.findByUserNameLike("%u%");

        System.out.println("findByUserNameLike()：" + userList2.size());

        Assert.isTrue(userList2.get(0).getUserName().equals("Sun"), "data error!");


        // 通过id列表查询数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);

        List<User> userList3 = userService.findByUserIdIn(ids);

        System.out.println("findByUserIdIn()：" + userList3.size());


        // 分页查询
        PageRequest pr = new PageRequest(0, 10);
        Page<User> userList4 = userService.findAll(pr);
        System.out.println("page findAll(): " + userList4.getTotalPages() + "/" + userList4.getSize());

        User user = new User();

        user.setUserId(2);
        user.setUserName("spring");
        user.setUserAccount("275514");
        user.setPassword("197400");
        user.setEmail("hello.qq.com");
        userService.save(user);

        // 删除数据
//        userService.delete(2);

    }


    @Test
    public void testTransaction() {

        User user = new User();

        user.setUserId(3);
        user.setUserName("Ethan");
        user.setUserAccount("275502");
        user.setPassword("666");

        userService.save(user);

    }


    @Test
    public void testRedis() {   // 测试Redis

        // 增：key：userName. value：Sun
        redisTemplate.opsForValue().set("userName", "Sun");
        String userName = (String) redisTemplate.opsForValue().get("userName");
        System.out.println(userName);

        // 删
        redisTemplate.delete("userName");

        // 改
        redisTemplate.opsForValue().set("userName", "AI");

        // 查
        userName = stringRedisTemplate.opsForValue().get("userName");

        System.out.println(userName);

    }


    @Test
    public void testRedisFindByUserId() {   // 测试将测试方法修改为Redis缓存后的情况

        Long redisUserSize = 0L;

        // 查询Id为1的数据存放到缓存Redis中
        User user = userService.findByUserId(1);

        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");

        System.out.println("目前缓存中的用户数量为：" + redisUserSize);

        System.out.println("-------->> id: " + user.getUserId() + "  ;userName: " + user.getUserName());


        // 查询Id为2的数据存放到缓存Redis中
        User user1 = userService.findByUserId(2);

        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");

        System.out.println("目前缓存中的用户数量为：" + redisUserSize);

        System.out.println("-------->> id: " + user1.getUserId() + "  ;userName: " + user1.getUserName());


        // 查询Id为4的数据
        // 由于不存在缓存中, 存在数据库中。所以，会把在数据库中查询到的数据更新到缓存中
        User user4 = userService.findByUserId(4);

        System.out.println("-------->> id: " + user4.getUserId() + "  ;userName: " + user4.getUserName());

        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");

        System.out.println("目前缓存中的用户数量为：" + redisUserSize);

    }


    @Test
    public void testLog4j2() {

        userService.delete(4);
        logger.info("delete number 4 successful!!");

    }


    @Test
    public void testMybatis() {

        User user = userService.findByUserNameAndPassword("Sun", "123");

        logger.info(user.getUserId() + user.getUserName());

    }


    @Test
    public void testMood() {     // 普通

        Mood mood = new Mood();

        mood.setMoodId(3);

        mood.setUserId(1);

        mood.setPraiseNum(0);

        mood.setContent("This is the first WeChat mood!\n");

        mood.setPublishTime(new Date());

        // 往数据库中存一条数据, 用户Sun发表了一条说说
//        Mood moodSun = moodService.save(mood);
        moodService.save(mood);

    }


    @Test
    public void testActiveMQ() {    // 使用消息队列

        Destination destination = new ActiveMQQueue("sun.queue");
        moodProducer.sendMessage(destination, "Hello, message queue!");

    }


    @Test
    public void testActiveMQAsynSave() {    // 消息队列：异步

        Mood mood = new Mood();

        mood.setMoodId(4);

        mood.setUserId(2);

        mood.setPraiseNum(0);

        mood.setContent("This is the first asyn WeChat message!\n");

        mood.setPublishTime(new Date());

        String msg = moodService.asynSave(mood);

        System.out.println("异步说说：" + msg);

    }


    @Test
    public void testAsync() {

        long startTime = System.currentTimeMillis();

        System.out.println("第一次查询所有用户！");
        List<User> userList0 = userService.findAll();

        System.out.println("第二次查询所有用户！");
        List<User> userList1 = userService.findAll();

        System.out.println("第三次查询所有用户！");
        List<User> userList2 = userService.findAll();

        long endTime = System.currentTimeMillis();

        System.out.println("总共消耗：" + (endTime - startTime) + "ms");

    }


    @Test
    public void testAsync0() throws Exception {

        long startTime = System.currentTimeMillis();

        System.out.println("第一次查询所有用户！");
        Future<List<User>> userList0 = userService.findAsyncAll();

        System.out.println("第二次查询所有用户！");
        Future<List<User>> userList1 = userService.findAsyncAll();

        System.out.println("第三次查询所有用户！");
        Future<List<User>> userList2 = userService.findAsyncAll();

        while (true) {

            if (userList0.isDone() && userList1.isDone() && userList2.isDone()) {

                break;

            } else {

                Thread.sleep(10);

            }

        }

        long endTime = System.currentTimeMillis();

        System.out.println("总共消耗：" + (endTime - startTime) + "ms");

    }

    @Test
    public void testMongoDB() {

        UserAttachmentRel userAttachmentRel = new UserAttachmentRel();

        userAttachmentRel.setId("4");
        userAttachmentRel.setUserId(2);
        userAttachmentRel.setFileName("personal biographical notes.doc");

        userAttachmentRelService.save(userAttachmentRel);

        System.out.println("save file to MongoDB successful!");

    }


}
