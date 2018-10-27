package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.RoleDeptDao;
import io.github.weechang.moreco.rbac.domain.RoleDeptDomain;
import io.github.weechang.moreco.rbac.service.RoleDeptService;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:17
 */
public class RoleDeptServiceImpl extends BaseServiceImpl<RoleDeptDao, RoleDeptDomain> implements RoleDeptService {
    @Override
    public List<Long> queryDeptIdList(Long[] roleIds) {
        return null;
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return 0;
    }
}
