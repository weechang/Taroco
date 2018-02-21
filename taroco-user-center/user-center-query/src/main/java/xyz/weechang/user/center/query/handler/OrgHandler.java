package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.user.center.error.UCError;
import xyz.weechang.user.center.event.org.OrgCreateEvent;
import xyz.weechang.user.center.event.org.OrgDeleteEvent;
import xyz.weechang.user.center.event.org.OrgUpdateEvent;
import xyz.weechang.user.center.query.dao.OrgDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:32.
 */
@Slf4j
@Component
@ProcessingGroup("org")
public class OrgHandler {

    @Autowired
    private OrgDao dao;

    @EventHandler
    public void on(OrgCreateEvent event) {
        int count = dao.findCountByParentAndName(event.getParentId(), event.getId());
        if (count > 0){
            log.error("org is exist parentId: {}, name: {}", event.getParentId(), event.getId());
            throw new BusinessException(UCError.ORG_IS_EXIST);
        }
        OrgEntry org = new OrgEntry();
        org.setId(event.getId());
        org.setName(event.getName());
        org.setParentId(event.getParentId());
        org = dao.save(org);
        if (StringUtils.isNotEmpty(event.getParentId())){
            OrgEntry parent = dao.findOne(event.getParentId());
            if (parent == null){
                log.error("parent org is not exist");
                throw new BusinessException(UCError.ORG_IS_NOT_EXIST);
            }
            List<OrgEntry> children = parent.getChildren();
            children.add(org);
            parent.setChildren(children);
            dao.save(parent);
        }
    }

    @EventHandler
    public void on(OrgUpdateEvent event) {
        OrgEntry org = dao.findOne(event.getId());
        if (org == null){
            throw new BusinessException(UCError.ORG_IS_NOT_EXIST);
        }
        org.setId(event.getId());
        org.setName(event.getName());
        org.setOrderNum(event.getOrderNum());
        dao.save(org);
    }

    @EventHandler
    public void on(OrgDeleteEvent event) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }
}
