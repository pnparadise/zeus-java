package com.framework.context.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author zhiqiang.huang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiResponse<T> {
    private int status;
    protected T data;
    protected String message;

    @JSONField(serialize = false)
    private HttpStatus httpStatus;

    public ApiResponse(T data) {
        this.data = data;
        this.httpStatus = HttpStatus.OK;
        this.message = HttpStatus.OK.getReasonPhrase();
        this.status = HttpStatus.OK.value();
    }


    public ApiResponse(HttpStatus httpStatus, Throwable error) {
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.message = error.getLocalizedMessage();

    }

    public ApiResponse(int status, Throwable error) {
        this.httpStatus = HttpStatus.OK;
        this.message = error.getMessage();
        this.status = status;
    }


}
