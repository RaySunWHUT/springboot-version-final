package control.sun.security;

import java.util.List;

public interface UserRoleRelService {

    List<UserRoleRel> findByUserId(Integer userId);

}
