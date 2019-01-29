package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.github.weechang.moreco.base.model.dto.QueryRequest;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("资源查询")
public class ResourceQueryRequest extends QueryRequest {
    private static final long serialVersionUID = 3082986066177529332L;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("权限标记")
    private String summary;

    public Resource toResource() {
        return BeanUtil.toBean(this, Resource.class);
    }
}
