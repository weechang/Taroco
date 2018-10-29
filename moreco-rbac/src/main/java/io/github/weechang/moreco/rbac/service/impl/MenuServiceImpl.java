package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.MenuDao;
import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.rbac.domain.RbacRole;
import io.github.weechang.moreco.rbac.domain.RbacUser;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:16
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, RbacMenu> implements MenuService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleMenuService roleMenuService;


    @Override
    public List<RbacMenu> findAllByParentId(Long parentId) {
        parentId = parentId == null ? 0L : parentId;
        return baseDao.findAllByParentId(parentId);
    }

    @Override
    public List<RbacMenu> findAllByUserId(Long userId) {
        List<RbacMenu> userMenus = null;
        // 获取用户角色
        RbacUser user = userService.findOne(userId);
        if (user != null) {
            userMenus = new ArrayList<>();
        }
        return userMenus;
    }

    @Override
    public RbacMenu save(RbacMenu menu) {
        long parentId = menu.getParentId() == null ? 0L : menu.getParentId();
        menu.setParentId(parentId);
        RbacMenu saved = baseDao.findFirstByNameAndParentId(menu.getName(), menu.getParentId());
        if (saved != null){
            if (menu.getId() == null || !menu.getId().equals(saved.getId())){
                throw new BusinessException(RbacError.MENU_EXISTED);
            }
        }
        return super.save(menu);
    }
}
