package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.ResourceIdEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:53
 */
public interface ResourceIdDao extends MongoBaseDao<ResourceIdEntity, String> {

    Optional<ResourceIdEntity> findOneByValue(String value);
}
