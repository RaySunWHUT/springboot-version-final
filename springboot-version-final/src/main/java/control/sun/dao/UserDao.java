package control.sun.dao;

import control.sun.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

@Mapper     // Dao映射接口
public interface UserDao {

    // 注解参数, 在Mapper配置文件中, 采用#{}方式对注解括号内的参数进行引用
    public User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

}
