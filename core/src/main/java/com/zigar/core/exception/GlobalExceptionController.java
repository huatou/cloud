package com.zigar.core.exception;

import com.zigar.core.model.Results;
import com.zigar.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * 创建全局异常处理类：通过使用@ControllerAdvice定义统一的异常处理类，而不是在每个Controller中逐个定义。
 * 该类只捕获一直到最上层未处理的异常，已处理的异常不会在此处拦截
 */
@ControllerAdvice
public class GlobalExceptionController {

    public static final String PROFILES_ACTIVE_DEV = "dev";
    public static final String PROFILES_ACTIVE_TEST = "test";
    public static final String PROFILES_ACTIVE_PROD = "prod";

    @Value("spring.profiles.active")
    private String profileActive;

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
        String errMsg = e.getMessage();
        if (e instanceof BusinessLogicException) {
            return Results.error(errMsg);
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
        } else if (e instanceof DuplicateKeyException) {
            // " Cause: java.sql.SQLIntegrityConstraintViolationException:Duplicate entry '15202089155' for key 'z_user_phone__uindex'
            String startStr = "Duplicate entry";
            String endStr = "for";
            Integer startIndex = StringUtils.indexOf(errMsg, startStr) + startStr.length() + 1;
            Integer endIndex = StringUtils.indexOf(errMsg, endStr);
            String duplicateStr = StringUtils.substring(errMsg, startIndex, endIndex);
            errMsg = StringUtils.append("违反数据库唯一约束：" + duplicateStr, "已存在", e.getMessage());
            return Results.error(errMsg);
        } else {
            errMsg = "系统内部异常";
            if (!StringUtils.equals(profileActive, PROFILES_ACTIVE_PROD)) {
                errMsg = StringUtils.append(errMsg, "【", e.getClass().toString(), "】：", errMsg);
            }
            e.printStackTrace();
            return Results.error(errMsg);
        }
    }

}