package xyz.weechang.user.center.query.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.weechang.taroco.base.dao.BaseDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

/**
 * 说明：组织机构查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:26.
 */
@RepositoryRestResource(collectionResourceRel = "org", path = "org")
public interface OrgDao extends BaseDao<OrgEntry, String> {

    OrgEntry findByCode(String code);
}
