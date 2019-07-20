package control.sun.service;

import control.sun.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 用户服务层接口
 */
public interface UserService {

    public User findByUserId(Integer userId);

    public List<User> findAll();

    public User save(User user);

    public void delete(Integer userId);

    // 分页
    public Page<User> findAll(Pageable pageable);

    public User findByUserName(String userName);

    public List<User> findByUserNameLike(String userName);

    public List<User> findByUserIdIn(Collection<Integer> ids);

    public User findByUserNameAndPassword(String userName, String password);

    public Future<List<User>> findAsyncAll();

    public User findByUserNameAndPasswordRetry(String userName, String password);

    // 查询用户数销量
    public Long findUserTotalNum();

}
