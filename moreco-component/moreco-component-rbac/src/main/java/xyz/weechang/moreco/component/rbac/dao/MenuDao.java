package xyz.weechang.moreco.component.rbac.dao;

import xyz.weechang.moreco.component.rbac.model.domain.Menu;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface MenuDao extends JpaBaseRepository<Menu> {

    List<Menu> findAllByParent(Menu parent);

    Menu findFirstByNameAndParent(String name, Menu parent);

    Menu findFirstByParentAndUrlAndType(Menu parent, String url, Integer type);

    List<Menu> findAllByParentAndType(Menu parent, Integer type);
}
