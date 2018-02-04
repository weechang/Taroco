package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.event.DeleteEvent;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.user.center.common.error.UCError;
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
    public void handle(OrgCreateEvent event) {
        int count = dao.findCountByParentAndName(event.getParentId(), event.getName());
        if (count > 0){
            log.error("org is exist parentId: {}, name: {}", event.getParentId(), event.getName());
            throw new BusinessException(UCError.ORG_IS_EXIST);
        }
        OrgEntry org = new OrgEntry();
        org.setId(event.getId());
        if (StringUtils.isNotEmpty(event.getParentId())){
            OrgEntry parent = dao.findOne(event.getParentId());
            org.setParent(parent);
        }
        org.setName(event.getName());
        org.setOrderNum(event.getOrderNum());
        dao.save(org);
        log.info("org is saved: {}", org.toString());
    }

    @EventHandler
    public void handle(OrgUpdateEvent event) {
        OrgEntry org = new OrgEntry();
        org.setId(event.getId());
        org.setOrderNum(event.getOrderNum());
        org.setOrderNum(event.getOrderNum());
        dao.save(org);
    }

    @EventHandler
    public void handle(DeleteEvent event) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }
}
