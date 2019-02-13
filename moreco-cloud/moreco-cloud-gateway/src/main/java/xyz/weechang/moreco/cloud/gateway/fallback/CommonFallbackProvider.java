package xyz.weechang.moreco.cloud.gateway.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 模块异常回调
 *
 * @author zhangwei
 * date 2019/2/3
 * time 22:32
 */
@Slf4j
@Component
public class CommonFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                if (cause != null && cause.getMessage() != null) {
                    log.error("服务:{} 异常：{}", route, cause.getMessage());
                    return new ByteArrayInputStream(cause.getMessage().getBytes());
                } else {
                    log.error("服务:{} 异常：{}", route, "暂不可用, 请稍候再试");
                    return new ByteArrayInputStream(("service:" + route + " not available, please try again later")
                            .getBytes
                                    ());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
