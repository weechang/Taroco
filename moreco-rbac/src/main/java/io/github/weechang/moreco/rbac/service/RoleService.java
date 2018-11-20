package io.github.weechang.moreco.rbac.service;

import io.github.weechang.jutil.common.util.DateUtil;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacDept;
import io.github.weechang.moreco.rbac.model.domain.RbacRole;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:58
 */
public interface RoleService extends BaseService<RbacRole> {

    /**
     * 属性转换
     *
     * @param roles 角色
     */
    static void convert2String(List<RbacRole> roles) {
        if (CollectionUtils.isNotEmpty(roles)) {
            for (RbacRole role : roles) {
                role.addDataMap("createdDate", DateUtil.format(role.getCreatedDate()));
            }
        }
    }

    /**
     * 查询角色详情
     *
     * @param id 角色id
     * @return 详情
     */
    RbacRole detail(Long id);
}
