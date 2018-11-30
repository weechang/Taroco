package io.github.weechang.moreco.monitor.manager.service;

import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.monitor.manager.model.domain.Server;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:25
 */
public interface ServerService extends BaseService<Server> {

    /**
     * 保存server
     *
     * @param server server
     * @return 返回
     */
    Server saveServer(Server server);

    PageModel<Server> findAll(Server server, PageRequest pageRequest);
}
