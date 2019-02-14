package xyz.moreco.component.oss;

import xyz.moreco.component.oss.config.OSSConfiguration;
import xyz.moreco.component.oss.enums.OSSEnum;

/**
 * 存储方案
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:27
 */
public abstract class StorageFactory {

    OSSConfiguration config;

    public StorageFactory(OSSEnum type){

    }

}
