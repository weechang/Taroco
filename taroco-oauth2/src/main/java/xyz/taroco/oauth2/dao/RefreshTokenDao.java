package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.RefreshTokenEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:53
 */
public interface RefreshTokenDao extends MongoBaseDao<RefreshTokenEntity, String> {


    Optional<RefreshTokenEntity> findOneByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);
}
