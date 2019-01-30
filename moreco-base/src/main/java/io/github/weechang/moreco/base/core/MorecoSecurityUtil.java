package io.github.weechang.moreco.base.core;

/**
 * @author zhangwei
 * date 2019/1/30
 * time 23:22
 */
public class MorecoSecurityUtil {

    private static MorecoSecurityContent content = MorecoSecurityContent.getInstance();


    /**
     * 设置登录信息
     *
     * @param username 用户名
     * @param userId   用户id
     */
    public static void setLoginInfo(String username, Long userId) {
        content.setLoginInfo(username, userId);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return content.getUsername();
    }

    /**
     * 获取用户id
     *
     * @return 用户id
     */
    public static Long getUserId() {
        return content.getUserId();
    }

    /**
     * 登出
     */
    public static void logout() {
        content.logout();
    }
}
