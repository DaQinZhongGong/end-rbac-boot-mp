package com.daqinzhonggong.exception.handler;

import com.daqinzhonggong.exception.BadRequestException;
import com.daqinzhonggong.exception.EntityExistException;
import com.daqinzhonggong.exception.EntityNotFoundException;
import com.daqinzhonggong.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * GlobalExceptionHandler类是一个全局异常处理器，通过定义多个@ExceptionHandler注解的方法，它能够对不同类型的异常进行统一处理，并返回格式化的错误信息。这种设计提高了代码的复用性和可维护性，同时也提升了用户体验。
 *
 * @author free
 */
// @Slf4j: Lombok提供的注解，用于自动注入日志对象。
@Slf4j
// @RestControllerAdvice: Spring框架提供的注解，用于定义一个全局异常处理器。
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有不可知的异常 @ExceptionHandler(Throwable.class) 表示该方法用于处理Throwable及其子类的异常
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiError> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    // @ExceptionHandler(BadCredentialsException.class) 表示该方法用于处理BadCredentialsException异常，通常用于认证失败的情况
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> badCredentialsException(BadCredentialsException e) {
        // 打印堆栈信息
        String message = "坏的凭证".equals(e.getMessage()) ? "用户名或密码不正确" : e.getMessage();
        log.error(message);
        return buildResponseEntity(ApiError.error(message));
    }

    // @ExceptionHandler(BadRequestException.class) 表示该方法用于处理BadRequestException异常,处理自定义的BadRequestException异常
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(BadRequestException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getStatus(), e.getMessage()));
    }

    // @ExceptionHandler(EntityExistException.class) 表示该方法用于处理EntityExistException异常，通常用于实体已存在的情况
    @ExceptionHandler(value = EntityExistException.class)
    public ResponseEntity<ApiError> entityExistException(EntityExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(e.getMessage()));
    }

    // @ExceptionHandler(EntityNotFoundException.class) 表示该方法用于处理EntityNotFoundException异常，通常用于实体未找到的情况
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> entityNotFoundException(EntityNotFoundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(ApiError.error(NOT_FOUND.value(), e.getMessage()));
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class) 表示该方法用于处理MethodArgumentNotValidException异常,处理接口数据验证异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        String message = objectError.getDefaultMessage();
        if (objectError instanceof FieldError) {
            message = ((FieldError) objectError).getField() + ": " + message;
        }
        return buildResponseEntity(ApiError.error(message));
    }

    // 构建响应实体,统一返回
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

}
