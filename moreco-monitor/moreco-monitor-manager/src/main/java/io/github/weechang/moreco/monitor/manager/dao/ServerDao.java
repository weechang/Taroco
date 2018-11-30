package io.github.weechang.moreco.monitor.manager.dao;

import io.github.weechang.moreco.base.dao.JpaDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Server;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:27
 */
public interface ServerDao extends JpaDao<Server> {

    Server findFirstByIp(String ip);
}
