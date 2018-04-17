package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.ClientDetailsEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:52
 */
public interface ClientDetailsDao extends MongoBaseDao<ClientDetailsEntity, String> {

    Optional<ClientDetailsEntity> findOneByClientId(String clientId);
}
