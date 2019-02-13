package xyz.weechang.moreco.component.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import xyz.weechang.moreco.core.model.dto.QueryRequest;
import xyz.weechang.moreco.component.rbac.model.domain.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("请求方法")
    private String method;

    public Resource toResource() {
        return BeanUtil.toBean(this, Resource.class);
    }
}
