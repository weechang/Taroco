package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.event.DeleteEvent;
import xyz.weechang.user.center.common.event.UserCreateEvent;
import xyz.weechang.user.center.common.event.UserUpdateEvent;
import xyz.weechang.user.center.query.dao.UserDao;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:33.
 */
@Slf4j
@ProcessingGroup("default")
@Component
public class UserHandler {

    @Autowired
    private UserDao dao;

    @EventHandler
    public void handle(UserCreateEvent event, @SequenceNumber Long version) {
        UserEntry user = new UserEntry();
        user.setId(event.getId());
        user.setUsername(event.getUsername());
        user.setPassword(event.getPassword());
        user.setPhone(event.getPhone());
        user.setEmail(event.getEmail());
        user.setEnable(event.getEnable());
        dao.save(user);
    }

    @EventHandler
    public void handle(UserUpdateEvent event, @SequenceNumber Long version) {
        UserEntry user = new UserEntry();
        user.setId(event.getId());
        user.setPassword(event.getPassword());
        user.setPhone(event.getPhone());
        user.setEmail(event.getEmail());
        user.setEnable(event.getEnable());
        dao.save(user);
    }

    @EventHandler
    public void handle(DeleteEvent event, @SequenceNumber Long version) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }

}
