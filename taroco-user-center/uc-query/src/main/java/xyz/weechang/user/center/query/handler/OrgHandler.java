package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.event.DeleteEvent;
import xyz.weechang.user.center.common.event.OrgCreateEvent;
import xyz.weechang.user.center.common.event.OrgUpdateEvent;
import xyz.weechang.user.center.query.dao.OrgDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:32.
 */
@Slf4j
@ProcessingGroup("default")
@Component
public class OrgHandler {

    @Autowired
    private OrgDao dao;

    @EventHandler
    public void handle(OrgCreateEvent event, @SequenceNumber Long version) {
        OrgEntry org = new OrgEntry();
        org.setId(event.getId());
        if (StringUtils.isNotEmpty(event.getParentId())){
            OrgEntry parent = dao.findOne(event.getParentId());
            org.setParent(parent);
        }
        org.setCode(event.getCode());
        org.setName(event.getName());
        org.setOrderNum(event.getOrderNum());
        org.setEnable(event.getEnable());
        org.setOpen(event.getOpen());
        dao.save(org);
    }

    @EventHandler
    public void handle(OrgUpdateEvent event, @SequenceNumber Long version) {
        OrgEntry org = new OrgEntry();
        org.setId(event.getId());
        org.setCode(event.getCode());
        org.setOrderNum(event.getOrderNum());
        org.setOrderNum(event.getOrderNum());
        org.setEnable(event.getEnable());
        org.setOpen(event.getOpen());
        dao.save(org);
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
