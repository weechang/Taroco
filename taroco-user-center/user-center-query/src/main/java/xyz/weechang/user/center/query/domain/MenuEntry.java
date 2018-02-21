package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.weechang.taroco.core.query.domain.BaseEntry;
import xyz.weechang.user.center.enums.MenuType;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：目录
 *
 * @author zhangwei
 * @version 2017/11/5 19:59.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Document
public class MenuEntry extends BaseEntry {

    private static final long serialVersionUID = -3480038672245433909L;

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
     * 类型(0：目录   1：菜单   2：按钮  4: tab)
     */
    private MenuType type = MenuType.MENU;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum = 1;

    /**
     * 上级ID
     */
    private String parentId;

    /**
     * 下级目录
     */
    @DBRef
    private List<MenuEntry> children = new ArrayList<MenuEntry>();
}
