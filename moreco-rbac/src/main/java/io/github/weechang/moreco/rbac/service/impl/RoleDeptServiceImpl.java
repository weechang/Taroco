package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDeptDao;
import io.github.weechang.moreco.rbac.domain.RbacRole;
import io.github.weechang.moreco.rbac.domain.RbacRoleDept;
import io.github.weechang.moreco.rbac.service.RoleDeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:17
 */
@Service
public class RoleDeptServiceImpl extends BaseServiceImpl<RoleDeptDao, RbacRoleDept> implements RoleDeptService {

    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }

    @Override
    public void save(Long roleId, List<Long> deptIds) {
        if (CollectionUtils.isEmpty(deptIds)) {
            return;
        }
        List<RbacRoleDept> saveds = baseDao.findAllByRoleId(roleId);

        List<Long> savedIds = saveds.stream().map(c -> c.getDeptId()).collect(Collectors.toList());

        List<Long> toSaveDeptIds = Lists.newArrayList();

        for (Long deptId : deptIds) {
            if (!savedIds.contains(deptId)) {
                toSaveDeptIds.add(deptId);
            } else {
                saveds.remove(savedIds.indexOf(deptId));
                savedIds.remove(deptId);
            }
        }

        // 批量保存
        if (CollectionUtils.isNotEmpty(toSaveDeptIds)) {
            List<RbacRoleDept> roleDepts = Lists.newArrayList();
            for (Long deptId : toSaveDeptIds) {
                RbacRoleDept roleDept = new RbacRoleDept();
                roleDept.setDeptId(deptId);
                roleDept.setRoleId(roleId);
                roleDepts.add(roleDept);
            }
            baseDao.save(roleDepts);
        }

        // 批量删除
        if (CollectionUtils.isNotEmpty(saveds)) {
            saveds.stream().forEach(s -> s.setYn(YnEnums.N.getKey()));
            baseDao.save(saveds);
        }
    }
}
