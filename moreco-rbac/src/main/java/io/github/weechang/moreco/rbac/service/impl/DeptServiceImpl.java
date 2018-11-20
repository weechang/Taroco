package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.exception.BusinessException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.DeptDao;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;
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
    public PageModel<RbacDept> findAllByParentId(Long parentId, Pageable pageable) {
        parentId = parentId == null ? 0L : parentId;
        return new PageModel<RbacDept>(baseDao.queryAllByParentId(parentId, pageable));
    }

    @Override
    public List<RbacDept> tree() {
        List<RbacDept> list = baseDao.findAllByParentId(0L);
        buildTree(list);
        return list;
    }

    /**
     * 构建机构树
     *
     * @param depts 机构
     */
    private void buildTree(List<RbacDept> depts){
        if (CollectionUtils.isNotEmpty(depts)){
            for (RbacDept dept: depts){
                List<RbacDept> children = baseDao.findAllByParentId(dept.getId());
                dept.setChildren(children);
                buildTree(children);
            }
        }
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
