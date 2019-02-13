package xyz.weechang.moreco.component.rbac.dao;

import xyz.weechang.moreco.component.rbac.model.domain.Dept;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:09
 */
public interface DeptDao extends JpaBaseRepository<Dept> {

    List<Dept> findAllByParent(Dept parent);

    Dept findFirstByNameAndParent(String name, Dept parent);
}
