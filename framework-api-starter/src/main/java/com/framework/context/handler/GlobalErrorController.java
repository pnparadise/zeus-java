
package com.framework.context.handler;

import com.framework.context.model.ApiEntity;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhiqiang.huang
 */
@RestController
@RequestMapping(value = "${server.error.path:$\\{error.path:/error}}")
public class GlobalErrorController implements ErrorController {

    private final ErrorProperties errorProperties;

    private final ErrorAttributes errorAttributes;

    /**
     * @param errorProperties error properties
     */
    public GlobalErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        this.errorProperties = errorProperties;
        this.errorAttributes = errorAttributes;
    }

    private static String getRequestUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        return uri;
    }

    protected Throwable getError(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Throwable error = errorAttributes.getError(webRequest);
        if (error == null) {
            return new NoHandlerFoundException(request.getMethod(), getRequestUri(request),
                    new ServletServerHttpRequest(request).getHeaders());
        }

        return error;
    }


    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.NOT_FOUND;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

    @RequestMapping
    public ApiEntity error(HttpServletRequest request) {
        return ApiEntity.error(this.getStatus(request), this.getError(request));
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }
}
