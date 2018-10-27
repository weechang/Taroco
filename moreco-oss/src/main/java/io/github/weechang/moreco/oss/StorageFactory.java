package io.github.weechang.moreco.oss;

import io.github.weechang.moreco.oss.config.OSSConfig;
import io.github.weechang.moreco.oss.enums.OSSEnums;

/**
 * 存储方案
 *
 * @author zhangwei
 * date 2018/10/27
 * time 14:27
 */
public abstract class StorageFactory {

    OSSConfig config;

    public StorageFactory(OSSEnums type){

    }

}
