package com.framework.context.handler;

import com.framework.context.model.ApiException;
import com.framework.context.model.ApiEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * @author zhiqiang.huang
 */
@Slf4j
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServletException.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity sqlException(Exception ex) {
        log.warn(ex.getMessage());
        if (ex instanceof NoHandlerFoundException) {
            return ApiEntity.error(HttpStatus.NOT_FOUND, ex);
        }

        if (ex instanceof ServletException) {
            return ApiEntity.error(HttpStatus.BAD_REQUEST, ex);
        }

        if (ex instanceof RuntimeException) {

            if (ex instanceof ApiException) {
                ApiException apiException = (ApiException) ex;
                return ApiEntity.error(apiException.getStatus(), ex);

            }

            if (ex instanceof IllegalArgumentException) {
                return ApiEntity.error(HttpStatus.BAD_REQUEST, ex);
            }
            if (ex instanceof TypeMismatchException) {
                return ApiEntity.error(HttpStatus.BAD_REQUEST, ex);
            }
        }

        log.error("error caught: ", ex);
        return ApiEntity.error(HttpStatus.INTERNAL_SERVER_ERROR, ex);

    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity catchOtherExceptions(Throwable ex) {
        log.error("error caught: ", ex);
        return ApiEntity.error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }


}
