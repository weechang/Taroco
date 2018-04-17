package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.UserEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

import java.util.Optional;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:54
 */
public interface UserDao extends MongoBaseDao<UserEntity, String> {

    Optional<UserEntity> findOneByUsername(String username);
}
