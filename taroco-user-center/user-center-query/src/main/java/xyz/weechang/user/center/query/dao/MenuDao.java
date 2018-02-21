package xyz.weechang.user.center.query.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import xyz.weechang.taroco.core.query.dao.MongoBaseDao;
import xyz.weechang.user.center.query.domain.MenuEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：目录查询
 *
 * @author zhangwei
 * @version 2017/11/5 20:24.
 */
public interface MenuDao extends MongoBaseDao<MenuEntry, String> {

    @Transactional(readOnly = true)
    @Query(value = "{'parentId':?0, 'name':?1, 'deleted':false}" ,count = true)
    int countByParentAndName(String parentId, String name);

    default List<MenuEntry> findByIds(List<String> ids) {
        List<MenuEntry> menus = new ArrayList<MenuEntry>();
        for (String id : ids){
            menus.add(findOne(id));
        }
        return menus;
    }
}
