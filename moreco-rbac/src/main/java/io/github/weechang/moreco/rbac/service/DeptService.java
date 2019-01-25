package io.github.weechang.moreco.rbac.service;

import cn.hutool.core.date.DateUtil;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.Dept;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:56
 */
public interface DeptService extends BaseService<Dept> {

    /**
     * 属性转换
     *
     * @param depts 部门
     */
    static void convert2String(List<Dept> depts) {
        if (CollectionUtils.isNotEmpty(depts)) {
            for (Dept dept : depts) {
                dept.addDataMap("createdDate", DateUtil.formatDateTime(dept.getCreatedDate()));
            }
        }
    }

    /**
     * 根据条件分页查询部门列表
     *
     * @param param    查询条件
     * @param pageable 分页条件
     * @return 部门列表
     */
    PageModel<Dept> findAll(Dept param, Pageable pageable);

    /**
     * 查询完整树结构
     *
     * @return 树结构
     */
    List<Dept> tree();
}
