package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.RedirectUriEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:53
 */
public interface RedirectUriDao extends MongoBaseDao<RedirectUriEntity, String> {

    Optional<RedirectUriEntity> findOneByValue(String value);
}
