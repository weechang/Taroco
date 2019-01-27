package io.github.weechang.moreco.rbac.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.rbac.model.domain.Resource;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 22:54
 */
public interface ResourceDao extends JpaDao<Resource> {

    Resource findFirstByPath(String path);
}
