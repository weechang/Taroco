package xyz.weechang.user.center.enums;

/**
 * 说明：目录类型
 *
 * @author zhangwei
 * @version 2018/2/16 13:20.
 */
public enum MenuType {
    MENU("目录"),
    ITEM("菜单"),
    BUTTON("按钮"),
    TAB("tab");

    private String type;

    MenuType(String type){
       this.type = type;
    }
}
