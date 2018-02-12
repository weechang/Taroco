package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.weechang.user.center.query.domain.TokenEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:54.
 */
public interface TokenDao extends MongoRepository<TokenEntry, String> {
}

