package control.sun.actuator;

import control.sun.service.UserService;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "userEndpoint")
@Component
public class UserEndpoint {

    @Resource
    private UserService userService;

    @ReadOperation
    public Map<String, Object> userNum() {

        Map<String, Object> map = new HashMap<String, Object>();

        // 当前时间
        map.put("currentTime", new Date());

        // 用户总数
        map.put("userNum", userService.findUserTotalNum());

        return map;

    }


    @Bean
    @ConditionalOnBean(LoggingSystem.class)
    @ConditionalOnMissingBean
    @ConditionalOnEnabledEndpoint
    public UserEndpoint userEndpoint() {

        return new UserEndpoint();

    }

}



