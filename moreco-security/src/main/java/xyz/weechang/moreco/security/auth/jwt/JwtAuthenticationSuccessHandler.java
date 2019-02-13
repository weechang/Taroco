package xyz.weechang.moreco.security.auth.jwt;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.weechang.moreco.core.model.dto.R;
import xyz.weechang.moreco.security.config.SecurityProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登录成功
 *
 * @author zhangwei
 * date 2019/1/27
 * time 13:53
 */
@Slf4j
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json; charset=utf-8");
        DecodedJWT jwt = ((JwtAuthenticationToken) auth).getToken();
        String token = jwt.getToken();
        res.setHeader(SecurityProperties.authKey, token);
        PrintWriter writer = null;
        try {
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            R r = R.ok(result);
            writer = res.getWriter();
            writer.write(JSONUtil.toJsonStr(r));
        } catch (Exception ex) {
            log.error("deal login success error");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
