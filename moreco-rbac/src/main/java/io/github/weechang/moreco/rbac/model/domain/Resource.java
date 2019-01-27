package io.github.weechang.moreco.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 21:00
 */
@ApiModel("后端接口资源")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_rbac_resource")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Resource extends BaseDomain {

    @ApiModelProperty("标签")
    @Column(name = "tag")
    private String tag;

    @ApiModelProperty("路径")
    @Column(name = "path")
    private String path;

    @ApiModelProperty("请求方法")
    @Column(name = "method")
    private String method;

    @ApiModelProperty("概要")
    @Column(name = "summary")
    private String summary;

    @JsonIgnore
    @ManyToMany(mappedBy = "resources")
    private List<Menu> menus = Lists.newArrayList();
}
