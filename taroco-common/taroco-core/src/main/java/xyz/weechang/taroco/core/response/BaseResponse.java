package xyz.weechang.taroco.core.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import xyz.weechang.taroco.core.error.IError;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:05
 */
@ApiModel("消息返回")
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -5359531292427290394L;

    @ApiModelProperty("错误码")
    private String errorCode;
    @ApiModelProperty("错误消息")
    private String errorMessage;
    @ApiModelProperty("附加消息")
    private String extMessage;
    @ApiModelProperty("操作状态")
    private BaseResponse.Status status;

    public BaseResponse.Status getStatus() {
        return this.status;
    }

    public void setStatus(BaseResponse.Status status) {
        this.status = status;
    }

    public BaseResponse() {
        this.status = BaseResponse.Status.SUCCEED;
    }

    public BaseResponse(IError error) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
        this.status = BaseResponse.Status.FAILED;
    }

    public static BaseResponse create() {
        return new BaseResponse();
    }

    public static BaseResponse create(IError error) {
        BaseResponse response = new BaseResponse();
        response.errorCode = error.getErrorCode();
        response.errorMessage = error.getErrorMessage();
        response.status = Status.FAILED;
        return response;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errorCode != null) {
            sb.append("ErrorCode : ").append(this.errorCode).append("ErrorMessage : ").append(this.errorMessage).append("ExtMessage : " + this.extMessage);
        } else {
            sb.append("Succeed");
        }

        return sb.toString();
    }

    public static enum Status {
        SUCCEED,
        FAILED;

        private Status() {
        }
    }
}
