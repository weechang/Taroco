package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacMenu;
import io.github.weechang.moreco.rbac.model.domain.enums.MenuTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:57
 */
public interface MenuService extends BaseService<RbacMenu> {

    /**
     * 属性转换
     *
     * @param menus 目录
     */
    static void convert2String(List<RbacMenu> menus) {
        if (CollectionUtils.isNotEmpty(menus)) {
            for (RbacMenu menu : menus) {
                menu.addDataMap("type", MenuTypeEnum.getNameByKey(menu.getType()));
            }
        }
    }

    /**
     * 根据父Id 查询所有子列表
     *
     * @param parentId 父Id
     * @return 子列表
     */
    PageModel<RbacMenu> findAllByParentId(Long parentId, Pageable pageable);

    /**
     * 查询完整树结构
     *
     * @return 树结构
     */
    List<RbacMenu> tree();
}
