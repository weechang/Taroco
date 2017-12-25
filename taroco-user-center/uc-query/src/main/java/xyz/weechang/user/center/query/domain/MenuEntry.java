package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.base.model.BaseEntry;

import javax.persistence.Entity;

/**
 * 说明：目录
 *
 * @author zhangwei
 * @version 2017/11/5 19:59.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class MenuEntry extends BaseEntry {

    private static final long serialVersionUID = -3480038672245433909L;

    /**
     * 上级目录
     */
    private MenuEntry parent;

    /**
     * 目录名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String uri;

    /**
     * 授权标识(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型(0：目录   1：菜单   2：按钮)
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * ztree属性
     */
    private Boolean open;
}
