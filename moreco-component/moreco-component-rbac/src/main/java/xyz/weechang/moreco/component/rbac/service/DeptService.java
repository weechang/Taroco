package xyz.weechang.moreco.component.rbac.service;

import xyz.weechang.moreco.core.model.dto.PageModel;
import xyz.weechang.moreco.core.service.BaseService;
import xyz.weechang.moreco.component.rbac.model.domain.Dept;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:56
 */
public interface DeptService extends BaseService<Dept> {

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
