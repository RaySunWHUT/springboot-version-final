package control.sun.serviceImpl;

import control.sun.dao.UserDao;
import control.sun.domain.User;
import control.sun.exception.BusinessException;
import control.sun.repository.UserRepository;
import control.sun.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;


//@Transactional  // 类级别事务
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private UserDao userDao;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ALL_USER = "ALL_USER_LIST";


    @Transactional  // 方法级别事务
    @Override
    public User save(User user) {

        User saveUser = userRepository.save(user);

        // 出现空指针
//        String error = null;
//
//        error.split("/");

        return saveUser;

    }

    @Override
    public User findByUserId(Integer userId) {  // 引入Redis, 进行修改后; 性能提高, 代码复杂度也提高; 应在性能和复杂度上做好权衡
        // 也可等同对save、delete等进行修改
        // step1: 查询Redis缓存中的所有数据
        List<User> userList = redisTemplate.opsForList().range(ALL_USER, 0, -1);

        if (userList != null && userList.size() > 0) {

            for (User user : userList) {

                if (user.getUserId().equals(userId)) {

                    return user;

                }

            }

        }

        // step2: 查询数据库中的数据
        User user = userRepository.findById(userId).get();

        if (user != null) {

            // step3: 将数据插入到Redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER, user);

        }

        return user;

    }

    @Override
    public List<User> findAll() {

        try {

            System.out.println("---------- Start tasks ----------");

            long start = System.currentTimeMillis();

            List<User> userList = userRepository.findAll();

            long end = System.currentTimeMillis();

            System.out.println("---------- Over tasks ---------- \n");
            System.out.println("耗时：" + (end - start) + "ms");

            return userList;

        } catch (Exception ex) {

            logger.error("method[findAll] error", ex);

            return Collections.EMPTY_LIST;

        }

    }


    @Override
    public void delete(Integer userId) {

        userRepository.deleteById(userId);

        logger.info("userId:" + userId + "用户被删除");  // 测试logger

    }


    @Override
    public Page<User> findAll(Pageable pageable) {

        return userRepository.findAll(pageable);

    }


    @Override
    public User findByUserName(String userName) {

        return userRepository.findByUserName(userName);

    }


    @Override
    public List<User> findByUserNameLike(String userName) {

        return userRepository.findByUserNameLike(userName);

    }


    @Override
    public List<User> findByUserIdIn(Collection<Integer> ids) {

        return userRepository.findByUserIdIn(ids);

    }


    @Override
    public User findByUserNameAndPassword(String userName, String password) {

        return userDao.findByUserNameAndPassword(userName, password);

    }

    @Override
    @Async
    public Future<List<User>> findAsyncAll() {

        try {

            System.out.println("---------- tasks Start ----------");

            long start0 = System.currentTimeMillis();

            List<User> userList0 = userRepository.findAll();

            long end0 = System.currentTimeMillis();

            System.out.println("---------- tasks Over ----------");

            System.out.println("耗时：" + (end0 - start0) + "ms");

            return new AsyncResult<List<User>>(userList0);

        } catch (Exception ex) {

            logger.error("method[findAll] error", ex);

            return new AsyncResult<List<User>>(null);

        }

    }


    @Override   // value：当出现哪些异常时触发重试; maxAttempts：最大重试次数(默认为3); backoff：delay, 重试的延迟时间; multiplier, 上一次延迟时间是这次的几倍
    @Retryable(value = {BusinessException.class}, maxAttempts = 5, backoff = @Backoff(delay = 5000, multiplier = 2))
    public User findByUserNameAndPasswordRetry(String userName, String password) {

        System.out.println("[findByUserNameAndPasswordRetry] 方法失败重试了！");

        throw new BusinessException();

    }


    @Override
    public Long findUserTotalNum() {

        return userRepository.count();

    }

}

