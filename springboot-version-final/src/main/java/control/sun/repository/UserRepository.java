package control.sun.repository;

import control.sun.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 精确查询, 参数为userName
     * 相当于select u from user u where u.userName = ?1
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName);


    /**
     * 模糊查询, 参数为userName
     * 相当于select u from user u where u.userName like ?1
     *
     * @param userName
     * @return
     */
    public List<User> findByUserNameLike(String userName);


    /**
     * 通过主键id集合查询, 参数为id集合
     * 相当于select u from user u where id in (?, ?, ?)
     *
     * @param ids
     * @return
     */
    public List<User> findByUserIdIn(Collection<Integer> ids);

}
