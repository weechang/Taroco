package io.github.weechang.moreco.monitor.manager.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Project;
/**
 * @author zhangwei
 * date 2018/11/27
 * time 14:24
 */
public interface ProjectDao extends JpaDao<Project> {

    Project findFirstByNameOrCode(String name, String code);
}
