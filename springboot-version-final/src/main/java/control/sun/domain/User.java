package control.sun.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * description: user table
 */

@Entity     // 持久化POJO类：实体Bean
@Table(name = "user")     // 声明对象映射到的数据库表user
public class User implements Serializable {

    @Id     // 主键
    private Integer userId;
    private String userName;
    private String userAccount;
    private String password;
    private String email;


    public Integer getUserId() {

        return userId;

    }

    public void setUserId(Integer userId) {

        this.userId = userId;

    }

    public String getUserName() {

        return userName;

    }

    public void setUserName(String userName) {

        this.userName = userName;

    }

    public String getUserAccount() {

        return userAccount;

    }

    public void setUserAccount(String userAccount) {

        this.userAccount = userAccount;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

}
