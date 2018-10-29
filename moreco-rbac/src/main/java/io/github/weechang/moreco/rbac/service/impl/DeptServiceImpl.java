package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.DeptDao;
import io.github.weechang.moreco.rbac.domain.RbacDept;
import io.github.weechang.moreco.rbac.domain.RbacMenu;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:14
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptDao, RbacDept> implements DeptService {

    @Override
    public List<RbacDept> findListByParentId(Long parentId) {
        parentId = parentId == null ? 0L : parentId;
        return baseDao.queryAllByParentId(parentId);
    }

    @Override
    public RbacDept save(RbacDept dept) {
        long parentId = dept.getParentId() == null ? 0L : dept.getParentId();
        dept.setParentId(parentId);
        RbacDept saved = baseDao.findFirstByNameAndParentId(dept.getName(), dept.getParentId());
        if (saved != null){
            if (dept.getId() == null || !dept.getId().equals(saved.getId())){
                throw new BusinessException(RbacError.DEPT_EXISTED);
            }
        }
        return super.save(dept);
    }
}
