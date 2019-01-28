package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
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

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty("资源名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("路径")
    @Column(name = "path")
    private String path;

    @ApiModelProperty("请求方法")
    @Column(name = "method")
    private String method;

    @ApiModelProperty("资源tag")
    @Column(name = "tag")
    private String tag;

    @ApiModelProperty("备注")
    @Column(name = "remark")
    private String remark;

    public Resource toResource(){
        return BeanUtil.toBean(this, Resource.class);
    }
}
