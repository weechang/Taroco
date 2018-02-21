package xyz.weechang.user.center.query.handler;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.user.center.constant.UserConstant;
import xyz.weechang.user.center.error.UCError;
import xyz.weechang.user.center.event.user.UserCreateEvent;
import xyz.weechang.user.center.event.user.UserDeleteEvent;
import xyz.weechang.user.center.event.user.UserUpdateEvent;
import xyz.weechang.user.center.query.dao.OrgDao;
import xyz.weechang.user.center.query.dao.RoleDao;
import xyz.weechang.user.center.query.dao.UserDao;
import xyz.weechang.user.center.query.domain.UserEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/5 20:33.
 */
@Slf4j
@ProcessingGroup("user")
@Component
public class UserHandler {

    @Autowired
    private UserDao dao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private RoleDao roleDao;

    @EventHandler
    public void on(UserCreateEvent event) {
        int count = dao.countByUsername(event.getUsername());
        if (count > 0) {
            log.error("user is exist {}", event.getUsername());
            throw new BusinessException(UCError.USER_IS_EXIST);
        }
        UserEntry user = new UserEntry();
        user.setId(event.getId());
        user.setUsername(event.getUsername());
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(event.getPassword());
        user.setPassword(password);
        user.setPhone(event.getPhone());
        user.setEmail(event.getEmail());
        if (event.getOrgs() != null) {
            user.setOrgs(orgDao.findByIds(event.getOrgs()));
        }
        if (event.getRoles() != null) {
            user.setRoles(roleDao.findByIds(event.getRoles()));
        }
        dao.save(user);
    }

    @EventHandler
    public void on(UserUpdateEvent event) {
        UserEntry user = new UserEntry();
        user.setId(event.getId());
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(event.getPassword());
        user.setPassword(password);
        user.setPhone(event.getPhone());
        user.setEmail(event.getEmail());
        if (event.getOrgs() != null) {
            user.setOrgs(orgDao.findByIds(event.getOrgs()));
        }
        if (event.getRoles() != null) {
            user.setRoles(roleDao.findByIds(event.getRoles()));
        }
        dao.save(user);
    }

    @EventHandler
    public void on(UserDeleteEvent event) {
        if (event.getLogic()) {
            dao.logicDelete(event.getId());
        }
    }

}
