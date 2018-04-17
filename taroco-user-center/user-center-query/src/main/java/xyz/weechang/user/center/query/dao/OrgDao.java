package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：组织机构查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:26.
 */
@Transactional(readOnly = true)
public interface OrgDao extends MongoBaseDao<OrgEntry, String> {

    @Query(value = "{'parentId':?0, 'name':?1, 'deleted':false}" ,count = true)
    int findCountByParentAndName(String parentId, String name);

    default List<OrgEntry> findByIds(List<String> ids) {
        List<OrgEntry> orgs = new ArrayList<OrgEntry>();
        for (String id : ids){
            orgs.add(findOne(id));
        }
        return orgs;
    }
}
