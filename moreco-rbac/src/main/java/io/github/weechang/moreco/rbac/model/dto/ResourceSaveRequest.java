package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:17
 */
@Data
@ApiModel("资源保存请求")
public class ResourceSaveRequest implements Serializable {
    private static final long serialVersionUID = 8216109177906081883L;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("权限标记")
    private String summary;

    public Resource toResource(){
        return BeanUtil.toBean(this, Resource.class);
    }
}
