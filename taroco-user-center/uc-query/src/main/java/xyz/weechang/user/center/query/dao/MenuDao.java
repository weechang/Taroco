package xyz.weechang.user.center.query.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xyz.weechang.taroco.base.dao.BaseDao;
import xyz.weechang.user.center.query.domain.MenuEntry;

/**
 * 说明：目录查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:24.
 */
@RepositoryRestResource(collectionResourceRel = "menu", path = "menu")
public interface MenuDao extends BaseDao<MenuEntry, String> {
}
