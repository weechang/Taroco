package io.github.weechang.moreco.rbac.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.DeptDao;
import io.github.weechang.moreco.rbac.domain.DeptDomain;
import io.github.weechang.moreco.rbac.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:14
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptDao, DeptDomain> implements DeptService {

    @Override
    public List<Long> queryDeptIdList(Long parentId) {
        List<Long> deptIds = new ArrayList<>();
        List<DeptDomain> depts = baseDao.queryAllByParentId(parentId);
        if (depts != null && depts.size() > 0) {
            for (DeptDomain dept : depts){
                deptIds.add(dept.getId());
            }
        }
        return deptIds;
    }

    @Override
    public List<Long> getSubDeptIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> deptIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryDeptIdList(deptId);
        getDeptTreeList(subIdList, deptIdList);

        return deptIdList;
    }

    /**
     * 递归
     */
    private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
        for(Long deptId : subIdList){
            List<Long> list = queryDeptIdList(deptId);
            if(list.size() > 0){
                getDeptTreeList(list, deptIdList);
            }

            deptIdList.add(deptId);
        }
    }
}
