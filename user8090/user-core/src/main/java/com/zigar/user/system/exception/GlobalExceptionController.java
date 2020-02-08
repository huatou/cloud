package com.zigar.user.system.exception;

import com.zigar.user.model.Results;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 该类只捕获一直到最上层未处理的异常，已处理的异常不会在此处拦截
 */
@ControllerAdvice
public class GlobalExceptionController {


    /**
     * 捕获自定义异常类型BusinessException，和@validated注释的验证不通过的方法
     *
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object ErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (e instanceof BusinessLogicException) {
            return Results.error(e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
            fieldErrorList.forEach(fieldError -> {
                stringBuilder.append(fieldError.getDefaultMessage()).append("\n");
            });
            return Results.error(stringBuilder.toString());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return Results.error("此接口未定义");
        } else {
            return Results.error("系统内部异常：" + e.getMessage());
        }
    }

}