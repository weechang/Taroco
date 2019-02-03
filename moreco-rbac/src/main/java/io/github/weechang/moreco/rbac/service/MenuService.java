package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 15:57
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 根据条件分页查询
     *
     * @param menu     查询条件
     * @param pageable 分页参数
     * @return 目录列表
     */
    PageModel<Menu> findAll(Menu menu, Pageable pageable);

    /**
     * 查询完整树结构
     *
     * @return 树结构
     */
    List<Menu> tree();

    /**
     * 查询授权目录
     *
     * @return 授权目录
     */
    List<Menu> permissionMenu(String username);

    /**
     * 查询授权目录树结构
     *
     * @param username 用户名
     * @return 授权目录树结构
     */
    List<Menu> permissionMenuTree(String username);

    /**
     * 根据目录路径，查询授权页面组件
     *
     * @param menuPath 目录路径
     * @param username 用户名
     * @return
     */
    List<Menu> permissionComponent(String menuPath, String username);
}
