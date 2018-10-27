package io.github.weechang.moreco.oss.cloud;

import io.github.weechang.moreco.oss.config.OSSConfig;

import java.io.InputStream;

/**
 * 文件上传类
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:39
 */
public abstract class StorageService {

    OSSConfig config;

    /**
     * 获取上传token
     *
     * @return 上传token
     */
    public abstract String uploadToken();

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data   文件字节数组
     * @param suffix 后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param suffix      后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);
}
