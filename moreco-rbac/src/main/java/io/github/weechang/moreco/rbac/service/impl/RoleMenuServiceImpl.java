package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleMenuDao;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleDept;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleMenu;
import io.github.weechang.moreco.rbac.service.RoleMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:20
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuDao, RbacRoleMenu> implements RoleMenuService {

    @Override
    public List<RbacRoleMenu> findAllByRoleId(Long id) {
        return baseDao.findAllByRoleId(id);
    }

    @Override
    public void save(Long roleId, List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return;
        }
        List<RbacRoleMenu> saveds = baseDao.findAllByRoleId(roleId);

        List<Long> savedIds = saveds.stream().map(c -> c.getMenuId()).collect(Collectors.toList());

        List<Long> toSaveMenuIds = Lists.newArrayList();

        for (Long menuId : menuIds) {
            if (!savedIds.contains(menuId)) {
                toSaveMenuIds.add(menuId);
            } else {
                saveds.remove(savedIds.indexOf(menuId));
                savedIds.remove(menuId);
            }
        }

        // 批量保存
        if (CollectionUtils.isNotEmpty(toSaveMenuIds)) {
            List<RbacRoleMenu> roleMenus = Lists.newArrayList();
            for (Long menuId : toSaveMenuIds) {
                RbacRoleMenu roleMenu = new RbacRoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(roleId);
                roleMenus.add(roleMenu);
            }
            baseDao.save(roleMenus);
        }

        // 批量删除
        if (CollectionUtils.isNotEmpty(saveds)) {
            saveds.stream().forEach(s -> s.setYn(YnEnums.N.getKey()));
            baseDao.save(saveds);
        }
    }
}
