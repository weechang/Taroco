package io.github.weechang.moreco.rbac.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.weechang.moreco.base.model.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 部门
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:50
 */
@ApiModel("部门")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_rbac_dept")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Dept extends BaseDomain {
    private static final long serialVersionUID = 1230574664359885255L;

    @ApiModelProperty("父级")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dept parent;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("排序")
    private Integer orderNum;

    @ApiModelProperty("用户")
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dept")
    private List<User> users;

    @ApiModelProperty("子级")
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Dept> children;
}
