package control.sun.security;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role find(Integer roleId) {

        return roleRepository.findById(roleId).get();

    }

}
