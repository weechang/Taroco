package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface MenuDao extends JpaDao<Menu> {

    List<Menu> findAllByParent(Menu parent);

    Menu findFirstByNameAndParent(String name, Menu parent);

    List<Menu> findAllByUrlAndType(String url, Integer type);

    List<Menu> findAllByParentAndType(Menu parent, Integer type);
}
