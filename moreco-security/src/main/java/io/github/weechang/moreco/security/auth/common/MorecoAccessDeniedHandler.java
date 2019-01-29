package io.github.weechang.moreco.security.auth.common;

import cn.hutool.json.JSONUtil;
import io.github.weechang.moreco.base.model.dto.R;
import io.github.weechang.moreco.security.error.SecurityError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义403响应内容
 *
 * @author zhangwei
 * date 2019/1/26
 * time 21:09
 */
@Slf4j
@Component
public class MorecoAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        R r = R.error(SecurityError.ACCESS_FORBIDDEN);
        PrintWriter writer = null;
        try {
            writer = res.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal access denied error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
