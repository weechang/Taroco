package xyz.weechang.user.center.query.dao;

import xyz.weechang.taroco.core.dao.BaseDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

/**
 * 说明：组织机构查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:26.
 */
public interface OrgDao extends BaseDao<OrgEntry, String> {

    OrgEntry findByCode(String code);
}
