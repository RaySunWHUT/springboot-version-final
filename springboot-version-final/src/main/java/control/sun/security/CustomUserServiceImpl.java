package control.sun.security;

import control.sun.domain.User;
import control.sun.exception.BusinessException;
import control.sun.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 自定义用户服务类
 */

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleRelService userRoleRelService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userService.findByUserName(userName);

        if (user == null) {

            throw new BusinessException("user not exist!");

        }

        // 获取用户所有的关联角色
        List<UserRoleRel> roleList = userRoleRelService.findByUserId(user.getUserId());

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

        if (roleList != null && roleList.size() > 0) {

            for (UserRoleRel rel : roleList) {

                String roleName = roleService.find(rel.getRoleId()).getRoleName();

                authorityList.add(new SimpleGrantedAuthority(roleName));

            }

        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorityList);

    }

}

