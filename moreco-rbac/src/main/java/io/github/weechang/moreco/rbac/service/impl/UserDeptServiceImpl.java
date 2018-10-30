package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.UserDeptDao;
import io.github.weechang.moreco.rbac.model.domain.RbacRoleDept;
import io.github.weechang.moreco.rbac.model.domain.RbacUserDept;
import io.github.weechang.moreco.rbac.service.UserDeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:22
 */
@Service
public class UserDeptServiceImpl extends BaseServiceImpl<UserDeptDao, RbacUserDept> implements UserDeptService {

    @Override
    public void save(Long userId, List<Long> deptIds) {
        if (CollectionUtils.isEmpty(deptIds)) {
            return;
        }
        List<RbacUserDept> saveds = baseDao.findAllByUserId(userId);

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
            List<RbacUserDept> userDepts = Lists.newArrayList();
            for (Long deptId : toSaveDeptIds) {
                RbacUserDept userDept = new RbacUserDept();
                userDept.setUserId(userId);
                userDept.setDeptId(deptId);
                userDepts.add(userDept);
            }
            baseDao.save(userDepts);
        }

        // 批量删除
        if (CollectionUtils.isNotEmpty(saveds)) {
            saveds.stream().forEach(s -> s.setYn(YnEnums.N.getKey()));
            baseDao.save(saveds);
        }
    }
}
