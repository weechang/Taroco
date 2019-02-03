package io.github.weechang.moreco.rbac.model.dto;

import cn.hutool.core.bean.BeanUtil;
import io.github.weechang.moreco.base.model.dto.QueryRequest;
import io.github.weechang.moreco.rbac.model.domain.Dept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangwei
 * date 2018/11/30
 * time 11:14
 */
@Data
@ApiModel("部门查询请求")
public class DeptQueryRequest extends QueryRequest {

    private static final long serialVersionUID = 561115640484647662L;

    @ApiModelProperty("上级部门ID，一级部门为0")
    private Long parentId;

    @ApiModelProperty("部门名称")
    private String name;

    public Dept toDept() {
        parentId = parentId == null ? 0L : parentId;
        Dept dept = BeanUtil.toBean(this, Dept.class);
        dept.setParent(new Dept(parentId));
        return dept;
    }
}
