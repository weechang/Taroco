package io.github.weechang.moreco.rbac.service;

import io.github.weechang.jutil.common.util.DateUtil;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:56
 */
public interface DeptService extends BaseService<RbacDept> {

    /**
     * 属性转换
     *
     * @param depts 部门
     */
    static void convert2String(List<RbacDept> depts) {
        if (CollectionUtils.isNotEmpty(depts)) {
            for (RbacDept dept : depts) {
                dept.addDataMap("createdDate", DateUtil.format(dept.getCreatedDate()));
            }
        }
    }

    /**
     * 查询子部门列表
     *
     * @param parentId 上级部门ID
     */
    PageModel<RbacDept> findAllByParentId(Long parentId, Pageable pageable);

    /**
     * 查询完整树结构
     *
     * @return 树结构
     */
    List<RbacDept> tree();
}
