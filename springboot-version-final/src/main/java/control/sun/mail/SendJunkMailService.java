package control.sun.mail;

import control.sun.domain.User;
import java.util.List;

/**
 * 发送用户邮件服务
 */
public interface SendJunkMailService {

    public boolean sendJunkMail(List<User> userList);

}
