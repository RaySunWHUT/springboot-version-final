package control.sun.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRelRepository extends JpaRepository<UserRoleRel, Integer> {

    List<UserRoleRel> findByUserId(@Param("userId") Integer userId);

}
