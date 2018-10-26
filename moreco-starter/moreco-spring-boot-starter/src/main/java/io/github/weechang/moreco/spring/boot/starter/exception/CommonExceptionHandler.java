package io.github.weechang.moreco.spring.boot.starter.exception;


import io.github.weechang.weechang.moreco.base.error.DefaultError;
import io.github.weechang.weechang.moreco.base.error.IError;
import io.github.weechang.weechang.moreco.base.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.util.List;
import java.util.Set;

/**
 * 统一异常处理
 *
 * @author weechang
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public BaseResponse handleError(Throwable e, HttpServletResponse servletResponse) {
        servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return build(e, servletResponse);
    }

    private BaseResponse build(Throwable ex, HttpServletResponse servletResponse) {
        IError error;
        String extMessage = null;
        if (ex instanceof BusinessException) {
            error = ((BusinessException) ex).getError();
            extMessage = ((BusinessException) ex).getExtMessage();
        } else if (ex instanceof BindException) {
            error = DefaultError.INVALID_PARAMETER;
            List<ObjectError> errors = ((BindException) ex).getAllErrors();
            if (errors != null && errors.size() != 0) {
                StringBuilder msg = new StringBuilder();
                for (ObjectError objectError : errors) {
                    msg.append("Field error in object '" + objectError.getObjectName() + " ");
                    if (objectError instanceof FieldError) {
                        msg.append("on field " + ((FieldError) objectError).getField() + " ");
                    }
                    msg.append(objectError.getDefaultMessage() + " ");
                }
                extMessage = msg.toString();
            }
        } else if (ex instanceof MissingServletRequestParameterException) {
            error = DefaultError.INVALID_PARAMETER;
            extMessage = ex.getMessage();
        } else if (ex instanceof ConstraintViolationException) {
            error = DefaultError.INVALID_PARAMETER;
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex).getConstraintViolations();
            final StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : violations) {
                msg.append(constraintViolation.getPropertyPath()).append(":").append(constraintViolation.getMessage() + "\n");
            }
            extMessage = msg.toString();
        } else if (ex instanceof HttpMediaTypeNotSupportedException) {
            error = DefaultError.CONTENT_TYPE_NOT_SUPPORT;
            extMessage = ex.getMessage();
        } else if (ex instanceof HttpMessageNotReadableException) {
            error = DefaultError.INVALID_PARAMETER;
            extMessage = ex.getMessage();
        } else if (ex instanceof MethodArgumentNotValidException) {
            error = DefaultError.INVALID_PARAMETER;
            extMessage = ex.getMessage();
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            error = DefaultError.METHOD_NOT_SUPPORTED;
            extMessage = ex.getMessage();
        } else if (ex instanceof UnexpectedTypeException) {
            error = DefaultError.INVALID_PARAMETER;
            extMessage = ex.getMessage();
        } else if (ex instanceof NoHandlerFoundException) {
            error = DefaultError.SERVICE_NOT_FOUND;
            extMessage = ex.getMessage();
        } else {
            error = DefaultError.SYSTEM_INTERNAL_ERROR;
            extMessage = ex.getMessage();
        }
        BaseResponse response = BaseResponse.create(error);
        response.setExtMessage(extMessage);
        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if (error == DefaultError.INVALID_PARAMETER) {
            status = HttpServletResponse.SC_BAD_REQUEST;
        } else if (error == DefaultError.METHOD_NOT_SUPPORTED) {
            status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
        } else if (error == DefaultError.SERVICE_NOT_FOUND) {
            status = HttpServletResponse.SC_NOT_FOUND;
        } else if (error == DefaultError.CONTENT_TYPE_NOT_SUPPORT) {
            status = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
        }
        servletResponse.setStatus(status);
        return response;
    }
}
