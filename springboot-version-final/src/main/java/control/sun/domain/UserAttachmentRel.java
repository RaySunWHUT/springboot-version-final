package control.sun.domain;

import javax.persistence.Id;

/**
 * 用户附件Bean
 */
public class UserAttachmentRel {

    @Id  // 主键
    private String id;

    private Integer userId;

    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
