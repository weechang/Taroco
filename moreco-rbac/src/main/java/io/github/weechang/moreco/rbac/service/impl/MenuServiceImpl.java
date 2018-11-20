package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.MenuDao;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.model.domain.RbacRole;
import io.github.weechang.moreco.rbac.model.domain.RbacUser;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.enums.MenuTypeEnum;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    public PageModel<RbacMenu> findAllByParentId(Long parentId, Pageable pageable) {
        parentId = parentId == null ? 0L : parentId;
        PageModel<RbacMenu> page = new PageModel<>(baseDao.findAllByParentId(parentId, pageable));
        return page;
    }

    @Override
    public List<RbacMenu> tree() {
        List<RbacMenu> list = baseDao.findAllByParentId(0L);
        buildTree(list);
        return list;
    }

    /**
     * 构建目录树
     *
     * @param menus 目录
     */
    private void buildTree(List<RbacMenu> menus){
        if (CollectionUtils.isNotEmpty(menus)){
            for (RbacMenu menu: menus){
                List<RbacMenu> children = baseDao.findAllByParentId(menu.getId());
                menu.setChildren(children);
                buildTree(children);
            }
        }
    }

    @Override
    public RbacMenu save(RbacMenu menu) {
        long parentId = menu.getParentId() == null ? 0L : menu.getParentId();
        menu.setParentId(parentId);
        RbacMenu saved = baseDao.findFirstByNameAndParentId(menu.getName(), menu.getParentId());
        if (saved != null) {
            if (menu.getId() == null || !menu.getId().equals(saved.getId())) {
                throw new BusinessException(RbacError.MENU_EXISTED);
            }
        }
        return super.save(menu);
    }
}
