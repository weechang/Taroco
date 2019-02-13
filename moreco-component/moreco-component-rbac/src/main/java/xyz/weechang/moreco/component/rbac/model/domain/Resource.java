package xyz.weechang.moreco.component.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import xyz.weechang.moreco.core.model.domain.BaseDomain;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "resources")
    private List<Menu> menus = Lists.newArrayList();
}
