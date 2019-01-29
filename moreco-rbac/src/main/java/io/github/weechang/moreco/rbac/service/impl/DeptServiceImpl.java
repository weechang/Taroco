package io.github.weechang.moreco.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.DeptDao;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.Dept;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:14
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptDao, Dept> implements DeptService {

    @Override
    public PageModel<Dept> findAll(Dept param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name" ,ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public List<Dept> tree() {
        List<Dept> list = baseDao.findAllByParentId(0L);
        buildTree(list);
        return list;
    }

    /**
     * 构建机构树
     *
     * @param depts 机构
     */
    private void buildTree(List<Dept> depts){
        if (CollectionUtil.isNotEmpty(depts)){
            for (Dept dept: depts){
                List<Dept> children = baseDao.findAllByParentId(dept.getId());
                dept.setChildren(children);
                buildTree(children);
            }
        }
    }

    @Override
    public Dept save(Dept dept) {
        long parentId = dept.getParentId() == null ? 0L : dept.getParentId();
        dept.setParentId(parentId);
        Dept saved = baseDao.findFirstByNameAndParentId(dept.getName(), dept.getParentId());
        if (saved != null){
            if (dept.getId() == null || !dept.getId().equals(saved.getId())){
                throw new AppException(RbacError.DEPT_EXISTED);
            }
        }
        return super.save(dept);
    }
}
