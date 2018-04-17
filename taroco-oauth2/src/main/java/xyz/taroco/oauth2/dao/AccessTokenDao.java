package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.AccessTokenEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.List;
import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:16
 */
public interface AccessTokenDao extends MongoBaseDao<AccessTokenEntity, String> {

    Optional<AccessTokenEntity> findOneByTokenId(String tokenId);

    Optional<AccessTokenEntity> findOneByAuthenticationId(String authenticationId);

    void deleteByTokenId(String tokenId);

    void deleteByRefreshTokenTokenId(String refreshTokenId);

    List<AccessTokenEntity> findAllByClientIdAndUserName(String clientId, String userName);

    List<AccessTokenEntity> findAllByClientId(String clientId);
}
