package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.ScopeEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:54
 */
public interface ScopeDao extends MongoBaseDao<ScopeEntity, String> {

    Optional<ScopeEntity> findOneByValue(String value);
}
