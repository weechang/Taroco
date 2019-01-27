package io.github.weechang.moreco.security.controller;

import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.security.error.SecurityError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户未登录
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:26
 */
@RestController
public class SecurityController {

    /**
     * 用户未登录
     *
     * @return 未登录提示
     */
    @GetMapping("/unLogin")
    public R unLogin() {
        return R.error(SecurityError.USER_NOT_LOGIN);
    }

    /**
     * 用户登录成功
     *
     * @return 登录成功
     */
    @GetMapping("/loginSuccess")
    public R loginSuccess() {
        return R.ok();
    }

    /**
     * 用户登录失败
     *
     * @return 登录失败
     */
    @GetMapping("/loginFailure")
    public R loginFailure() {
        return R.error(SecurityError.LOGIN_ERROR);
    }
}
