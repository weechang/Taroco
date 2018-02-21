package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.user.center.error.UCError;
import xyz.weechang.user.center.event.role.RoleCreateEvent;
import xyz.weechang.user.center.event.role.RoleDeleteEvent;
import xyz.weechang.user.center.event.role.RoleUpdateEvent;
import xyz.weechang.user.center.query.dao.MenuDao;
import xyz.weechang.user.center.query.dao.RoleDao;
import xyz.weechang.user.center.query.domain.RoleEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:32.
 */
@Slf4j
@ProcessingGroup("role")
@Component
public class RoleHandler {

    @Autowired
    private RoleDao dao;

    @Autowired
    private MenuDao menuDao;

    @EventHandler
    public void on(RoleCreateEvent event){
        int count = dao.countByName(event.getName());
        if (count > 0 ){
            log.error("role is exist {}", event.getName());
            throw new BusinessException(UCError.ROLE_IS_EXIST);
        }
        RoleEntry role = new RoleEntry();
        role.setId(event.getId());
        role.setName(event.getName());
        role.setRemark(event.getRemark());
        if (event.getMenus() != null){
            role.setMenus(menuDao.findByIds(event.getMenus()));
        }
        dao.save(role);
    }

    @EventHandler
    public void on(RoleUpdateEvent event){
        RoleEntry role = dao.findOne(event.getId());
        if (role == null){
            log.error("role is not exist {}", event.getId());
            throw new BusinessException(UCError.ROLE_IS_NOT_EXIST);
        }
        role.setName(event.getName());
        role.setRemark(event.getRemark());
        if (event.getMenus() != null){
            role.setMenus(menuDao.findByIds(event.getMenus()));
        }
        dao.save(role);
    }

    @EventHandler
    public void handle(RoleDeleteEvent event) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }
}
