package xyz.weechang.moreco.component.rbac.dao;

import xyz.weechang.moreco.component.rbac.model.domain.Resource;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 22:54
 */
public interface ResourceDao extends JpaBaseRepository<Resource> {

    Resource findFirstByPath(String path);
}
