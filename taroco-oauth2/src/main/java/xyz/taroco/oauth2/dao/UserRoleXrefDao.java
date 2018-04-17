package xyz.taroco.oauth2.dao;

import xyz.taroco.oauth2.domain.AccessTokenEntity;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;

/**
 * @author zhangwei
 * date 2018/4/16
 * time 16:55
 */
public interface UserRoleXrefDao extends MongoBaseDao<AccessTokenEntity, String> {
}
