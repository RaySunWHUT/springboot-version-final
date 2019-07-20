package control.sun.error;

import control.sun.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 统一业务异常处理
 */
@ControllerAdvice(basePackages = {"season.spring",})   // 定义统一的异常处理类, basePackages属性用于定义扫描哪些包
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})    // 用来定义函数针对的异常类型, 可以传入多个需要捕获的异常类
    @ResponseBody   // 若返回类型为json数据或其他对象, 则添加该注解
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {

        ErrorInfo errorInfo = new ErrorInfo();

        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);

        return errorInfo;

    }

}
