package control.sun.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleRelService {

    @Resource
    private UserRoleRelRepository userRoleRelRepository;


    @Override
    public List<UserRoleRel> findByUserId(Integer userId) {

        return userRoleRelRepository.findByUserId(userId);

    }

}
