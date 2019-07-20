package control.sun.error;

/**
 * 错误信息类
 *
 * @param <T>
 */
public class ErrorInfo<T> {

    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 100;

    // 错误信息
    private Integer code;

    // 错误码
    private String message;

    private String url;

    private T data;

    public static Integer getSUCCESS() {
        return SUCCESS;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
