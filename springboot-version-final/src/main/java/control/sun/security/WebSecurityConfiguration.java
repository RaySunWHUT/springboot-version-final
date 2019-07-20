package control.sun.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * security配置类
 */
@Configuration
@EnableWebSecurity  // 开启Security安全框架
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean   // 将customUserService装进Spring容器
    public CustomUserServiceImpl customUserService() {

        return new CustomUserServiceImpl();

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // 重写configure方法

//        // 路由策略和访问权限的简单配置
//        http.formLogin()    // 启动默认登录页面
//                .failureUrl("/login?error") // 登陆失败, 返回URL：/login?error
//                .defaultSuccessUrl("/user/findAll")    // 登陆成功跳转URL, 这里跳转到用户首页
//                .permitAll();   // 登陆页面全部权限可访问
//
//        super.configure(http);

        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();

    }

    /**
     * 配置内存用户
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // inMemoryAuthentication()可添加内存中的用户, 并给用户指定角色权限
        auth.userDetailsService(customUserService());
//            .inMemoryAuthentication()
//            .withUser("Sun").password("123").roles("ADMIN")
//            .and()
//            .withUser("Ethan").password("666").roles("USER");

    }

}


