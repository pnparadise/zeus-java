package com.framework.context.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author zhiqiang.huang
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiEntity<T> extends ResponseEntity<ApiResponse<T>> {

    private ApiEntity(ApiResponse<T> jsonResponse){
        super(jsonResponse, HttpStatus.OK);
    }

    public static ApiEntity error(HttpStatus httpStatus, Throwable ex){
        return new ApiEntity<>(new ApiResponse<>(httpStatus, ex));
    }

    public static<T> ApiEntity<T> error(HttpStatus httpStatus, T data){
        ApiResponse<T> resp = new ApiResponse<>(data);
        resp.setHttpStatus(httpStatus);
        resp.setMessage(httpStatus.getReasonPhrase());
        return new ApiEntity<>(resp);
    }

    public static ApiEntity error(int status, Throwable ex){
        return new ApiEntity<>(new ApiResponse<>(status, ex));
    }

    public static<T> ApiEntity ok(T data){
        return new ApiEntity<>(new ApiResponse<>(data));
    }
}

