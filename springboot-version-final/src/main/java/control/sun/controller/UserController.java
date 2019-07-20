package control.sun.controller;

import control.sun.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
//@Controller
//@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

//    @RequestMapping("/test")
//    public String test(Model model) {
//
//        // 查询数据库中的所有用户
//        List<User> userList = userService.findAll();
//
//        model.addAttribute("users", userList);
//
//        return "user";
//
//    }
//
//
//    @RequestMapping("/findAll")
//    public String findAll(Model model) {
//
//        List<User> userList = userService.findAll();
//
//        model.addAttribute("userList", userList);
//
//        throw new BusinessException("业务异常！");
//
//    }
//
//
//    @RequestMapping("/findByUserNameAndPasswordRetry")
//    public String findByUserNameAndPasswordRetry(Model model) {
//
//        User user = userService.findByUserNameAndPasswordRetry("Sun", "123");
//        model.addAttribute("user", user);
//
//        return "success";
//
//    }


    @GetMapping("/hello")
    public String hello() {

        return "Hello SpringBoot!\n";

    }

}
