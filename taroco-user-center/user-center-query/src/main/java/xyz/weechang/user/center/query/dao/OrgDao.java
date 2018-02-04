package xyz.weechang.user.center.query.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.dao.BaseDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

/**
 * 说明：组织机构查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:26.
 */
public interface OrgDao extends BaseDao<OrgEntry, String> {

    @Transactional(readOnly = true)
    @Query("select count(1) from OrgEntry o where o.parent.id = ?1 and o.name = ?2 and o.deleted = false")
    int findCountByParentAndName(String parentId, String name);
}
