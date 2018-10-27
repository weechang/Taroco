package io.github.weechang.moreco.oss.cloud;

import java.io.InputStream;

/**
 * 阿里云实现
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:26
 */
public class Aliyunservice extends StorageService {

    @Override
    public String uploadToken() {
        return null;
    }

    @Override
    public String upload(byte[] data, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return null;
    }
}
