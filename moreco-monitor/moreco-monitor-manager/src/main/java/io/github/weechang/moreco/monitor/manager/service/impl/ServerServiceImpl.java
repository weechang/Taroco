package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.error.SysError;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.ServerDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Server;
import io.github.weechang.moreco.monitor.manager.service.ServerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:35
 */
@Service
public class ServerServiceImpl extends BaseServiceImpl<ServerDao, Server> implements ServerService {

    @Override
    public Server saveServer(Server server) {
        if (server.getIp() == null){
            throw new AppException(SysError.PARAMETER_REQUIRED);
        }
        Server saved = baseDao.findFirstByIp(server.getIp());
        if (saved != null){
            server.setId(saved.getId());
        }
        return super.save(server);
    }

    @Override
    public PageModel<Server> findAll(Server server, PageRequest pageRequest) {
        baseDao.findAll(Example.of(server), pageRequest);
        return null;
    }
}
