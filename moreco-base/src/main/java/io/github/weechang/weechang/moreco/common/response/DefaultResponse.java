package io.github.weechang.weechang.moreco.common.response;

import lombok.Data;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/10/21 19:16.
 */
@Data
public class DefaultResponse<T extends Object> extends BaseResponse{

    private static final long serialVersionUID = 3089427141755212849L;

    T result;
}
