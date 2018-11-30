package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserRoleDao;
import io.github.weechang.moreco.rbac.model.domain.UserRole;
import io.github.weechang.moreco.rbac.service.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:23
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> findAllByUserId(Long id) {
        return baseDao.findAllByUserId(id);
    }

    @Override
    public void save(Long userId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return;
        }
        List<UserRole> saveds = baseDao.findAllByUserId(userId);

        List<Long> savedIds = saveds.stream().map(c -> c.getRoleId()).collect(Collectors.toList());

        List<Long> toSaveRoleIds = Lists.newArrayList();

        for (Long roleId : roleIds) {
            if (!savedIds.contains(roleId)) {
                toSaveRoleIds.add(roleId);
            } else {
                saveds.remove(savedIds.indexOf(roleId));
                savedIds.remove(roleId);
            }
        }

        // 批量保存
        if (CollectionUtils.isNotEmpty(toSaveRoleIds)) {
            List<UserRole> userRoles = Lists.newArrayList();
            for (Long roleId : toSaveRoleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }
            baseDao.save(userRoles);
        }

        // 批量删除
        if (CollectionUtils.isNotEmpty(saveds)) {
            saveds.stream().forEach(s -> s.setYn(YnEnums.N.getKey()));
            baseDao.save(saveds);
        }
    }
}
