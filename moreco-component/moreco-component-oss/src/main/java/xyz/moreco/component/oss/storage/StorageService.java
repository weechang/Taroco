package xyz.moreco.component.oss.storage;

import xyz.moreco.component.oss.config.OssProperties;

import java.io.InputStream;

/**
 * 文件上传类
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:39
 */
public abstract class StorageService {

    OssProperties config;

    /**
     * 获取上传token
     *
     * @param bucketName 空间名
     * @return 上传token
     */
    public abstract String getUploadToken(String bucketName);

    /**
     * 文件上传
     *
     * @param data       文件字节数组
     * @param bucketName 空间名
     * @param path       文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String bucketName, String path);

    /**
     * 文件上传
     *
     * @param data       文件字节数组
     * @param bucketName 空间名
     * @param suffix     后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String bucketName, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param bucketName  空间名
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String bucketName, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param bucketName  空间名
     * @param suffix      后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String bucketName, String suffix);
}
