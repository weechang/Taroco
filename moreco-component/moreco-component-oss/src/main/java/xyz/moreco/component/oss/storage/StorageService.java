package xyz.moreco.component.oss.storage;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang.StringUtils;
import xyz.moreco.component.oss.config.OssProperties;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

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
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + "/" + uuid;
        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path + suffix;
    }

    /**
     * 获取上传token
     *
     * @return 上传token
     */
    public abstract String getUploadToken();

    /**
     * 文件上传
     *
     * @param data       文件字节数组
     * @param path       文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     *
     * @param data       文件字节数组
     * @param suffix     后缀
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
