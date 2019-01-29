package io.github.weechang.moreco.rbac.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:58
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 属性转换
     *
     * @param roles 角色
     */
    static void convert2String(List<Role> roles) {
        if (CollectionUtil.isNotEmpty(roles)) {
            for (Role role : roles) {
                role.addDataMap("createdDate", DateUtil.formatDateTime(role.getCreatedDate()));
            }
        }
    }

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
