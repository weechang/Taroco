package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.base.event.DeleteEvent;
import xyz.weechang.user.center.query.dao.RoleDao;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:32.
 */
@Slf4j
@ProcessingGroup("default")
@Component
public class RoleHandler {

    @Autowired
    private RoleDao dao;

    @EventHandler
    public void handle(DeleteEvent event, @SequenceNumber Long version) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }
}
