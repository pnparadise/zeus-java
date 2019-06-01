package com.framework.context.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhiqiang.huang
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class ApiException extends RuntimeException {
    protected int status;
    protected String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
