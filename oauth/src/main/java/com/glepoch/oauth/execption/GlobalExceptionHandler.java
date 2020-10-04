package com.glepoch.oauth.execption;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常控制类
 */
@RestController
public class GlobalExceptionHandler {

    /**
     * 404异常处理
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String errorHandler(HttpServletRequest request, NoHandlerFoundException exception, HttpServletResponse response) {
        return commonHandler(request, response,
                exception.getClass().getSimpleName(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage());
    }

    /**
     * 405异常处理
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String errorHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception, HttpServletResponse response) {
        return commonHandler(request, response,
                exception.getClass().getSimpleName(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage());
    }

    /**
     * 415异常处理
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public String errorHandler(HttpServletRequest request, HttpMediaTypeNotSupportedException exception, HttpServletResponse response) {
        return commonHandler(request, response,
                exception.getClass().getSimpleName(),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                exception.getMessage());
    }

    /**
     * 500异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public String errorHandler (HttpServletRequest request, Exception exception, HttpServletResponse response) {
        return commonHandler(request, response,
                exception.getClass().getSimpleName(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(value = BasicException.class)
    private String errorHandler (HttpServletRequest request, BasicException exception, HttpServletResponse response) {
        return commonHandler(request, response,
                exception.getClass().getSimpleName(),
                exception.getCode(),
                exception.getMessage());
    }

    /**
     * 表单验证异常处理
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ExceptionEntity validExceptionHandler(BindException exception, HttpServletRequest request, HttpServletResponse response) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String,String> errors = new HashMap<>();
        for (FieldError error:fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ExceptionEntity entity = new ExceptionEntity();
        entity.setMessage(JSON.toJSONString(errors));
        entity.setPath(request.getRequestURI());
        entity.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        entity.setError(exception.getClass().getSimpleName());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return entity;
    }

    /**
     * 异常处理数据处理
     */
    private String commonHandler (HttpServletRequest request, HttpServletResponse response,
                                            String error, int httpCode, String message) {
        ExceptionEntity entity = new ExceptionEntity();
        entity.setPath(request.getRequestURI());
        entity.setError(error);
        entity.setCode(httpCode);
        entity.setMessage(message);
        return JSON.toJSONString(entity);
    }

}
