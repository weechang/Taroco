package xyz.moreco.component.oss.storage;

import cn.hutool.core.io.IoUtil;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import xyz.moreco.component.oss.config.OssProperties;
import xyz.moreco.component.oss.config.QiniuProperties;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * 七牛云实现
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:26
 */
public class QiniuStorageService extends StorageService{

    private UploadManager uploadManager;
    private String token;

    @Resource
    private QiniuProperties qiniuProperties;

    public QiniuStorageService(OssProperties config) {
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey()).uploadToken(qiniuProperties.getBucketName());
    }

    @Override
    public String getUploadToken() {
        return this.token;
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
        }
        return qiniuProperties.getDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IoUtil.readBytes(inputStream);
            return this.upload(data, path);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(qiniuProperties.getPrefix(), suffix));
    }
}
