package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:10
 */
public interface MenuDao extends JpaDao<Menu> {

    List<Menu> findAllByParent(Menu parent);

    Page<Menu> findAllByParent(Menu parent, Example<Menu> example, Pageable pageable);

    Menu findFirstByNameAndParent(String name, Menu parent);

    List<Menu> findAllByUrlAndType(String url, Integer type);

    List<Menu> findAllByParentAndType(Menu parent, Integer type);
}
