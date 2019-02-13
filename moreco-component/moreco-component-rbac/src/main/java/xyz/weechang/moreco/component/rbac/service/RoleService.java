package xyz.weechang.moreco.component.rbac.service;

import xyz.weechang.moreco.component.rbac.model.domain.Role;
import xyz.weechang.moreco.core.model.dto.PageModel;
import xyz.weechang.moreco.core.service.BaseService;
import org.springframework.data.domain.Pageable;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:58
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据条件分页查询
     *
     * @param param    查询条件
     * @param pageable 分页参数
     * @return 结果
     */
    PageModel<Role> findAll(Role param, Pageable pageable);

    /**
     * 查询角色详情
     *
     * @param id 角色id
     * @return 详情
     */
    Role detail(Long id);

}
