package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.user.center.error.UCError;
import xyz.weechang.user.center.event.menu.MenuCreateEvent;
import xyz.weechang.user.center.event.menu.MenuDeleteEvent;
import xyz.weechang.user.center.event.menu.MenuUpdateEvent;
import xyz.weechang.user.center.query.dao.MenuDao;
import xyz.weechang.user.center.query.domain.MenuEntry;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:32.
 */
@Slf4j
@Component
@ProcessingGroup("menu")
public class MenuHandler {

    @Autowired
    private MenuDao dao;

    @EventHandler
    public void on(MenuCreateEvent event){
        int count = dao.countByParentAndName(event.getParentId(), event.getId());
        if (count > 0){
            log.error("org is exist parentId: {}, name: {}", event.getParentId(), event.getId());
            throw new BusinessException(UCError.MENU_IS_EXIST);
        }
        MenuEntry menu = new MenuEntry();
        menu.setId(event.getId());
        menu.setName(event.getName());
        menu.setIcon(event.getIcon());
        menu.setPerms(event.getPerms());
        menu.setUri(event.getUri());
        menu.setType(event.getType());
        menu.setParentId(event.getParentId());
        dao.save(menu);
        if (StringUtils.isNotEmpty(event.getParentId())){
            MenuEntry parent = dao.findOne(event.getParentId());
            if (parent == null){
                log.error("parent menu is not exist");
                throw new BusinessException(UCError.MENU_IS_NOT_EXIST);
            }
            List<MenuEntry> children = parent.getChildren();
            children.add(menu);
            parent.setChildren(children);
            dao.save(parent);
        }
    }

    @EventHandler
    public void on(MenuUpdateEvent event){
        MenuEntry menu = dao.findOne(event.getId());
        if (menu == null){
            log.error("menu is not exist {}", event.getId());
            throw new BusinessException(UCError.MENU_IS_NOT_EXIST);
        }
        menu.setId(event.getId());
        menu.setName(event.getName());
        menu.setUri(event.getUri());
        menu.setPerms(event.getPerms());
        menu.setType(event.getType());
        menu.setIcon(event.getIcon());
        menu.setOrderNum(event.getOrderNum());
        dao.save(menu);
    }

    @EventHandler
    public void on(MenuDeleteEvent event) {
        if (event.getLogic()){
            dao.logicDelete(event.getId());
        } else {
            dao.delete(event.getId());
        }
    }
}
