package io.github.weechang.moreco.base.exception;


import io.github.weechang.moreco.base.error.SysError;
import io.github.weechang.moreco.base.error.IError;

/**
 * 说明：
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:47
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -6293662498600553602L;

    private IError error;
    private String extMessage;

    public AppException() {
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
    }

    public AppException(String message) {
        super(message);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.extMessage = message;
    }

    public AppException(Throwable cause) {
        super(cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
    }

    public AppException(IError error) {
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public AppException(String message, IError error) {
        super(message);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = message;
        this.error = error;
    }

    public AppException(String message, Throwable cause, IError error) {
        super(message, cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = message;
        this.error = error;
    }

    public AppException(Throwable cause, IError error) {
        super(cause);
        this.error = SysError.SYSTEM_INTERNAL_ERROR;
        this.extMessage = null;
        this.error = error;
    }

    public IError getError() {
        return this.error;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    @Override
    public String toString() {
        return super.toString() + ",ns:" + this.error.getNs() + ",code : " + this.error.getCode() + ", msg : " + this.error.getMsg() + ", ExtMessage : " + this.extMessage;
    }
}
