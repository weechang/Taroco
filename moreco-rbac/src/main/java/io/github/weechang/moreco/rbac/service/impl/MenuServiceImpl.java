package io.github.weechang.moreco.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.MenuDao;
import io.github.weechang.moreco.rbac.dao.UserDao;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.domain.enums.MenuTypeEnum;
import io.github.weechang.moreco.rbac.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:16
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MenuDao menuDao;

    @Override
    public PageModel<Menu> findAll(Menu param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<Menu> page = new PageModel<>(menuDao.findAll(Example.of(param, matcher), pageable));
        return page;
    }

    @Override
    public List<Menu> tree() {
        List<Menu> list = baseDao.findAllByParent(null);
        return list;
    }

    @Override
    public List<Menu> permissionMenu(String username) {
        List<Menu> permissionMenus = null;
        User user = userDao.findFirstByUsername(username);
        if (user != null) {
            List<Role> roles = user.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                permissionMenus = new ArrayList<>();
                for (Role role : roles) {
                    permissionMenus.addAll(role.getMenus());
                }
            }
        }
        return permissionMenus;
    }

    @Override
    public List<Menu> permissionMenuTree(String username) {
        List<Menu> tree = this.tree();
        List<Menu> permissionMenus = permissionMenu(username);
        removeUnPermission(tree, permissionMenus);
        return tree;
    }

    /**
     * 移除未授权目录
     *
     * <p>
     * 判断根节点是否已授权，如果是，则说明其下所有子节点均已授权，则不必过滤
     * </p>
     * <p>
     * 过滤所有子节点，剔除未授权子节点。若子节点均为授权，那么根节点也不应返回
     * </p>
     *
     * @param menus           目录树
     * @param permissionMenus 授权目录
     */
    private void removeUnPermission(List<Menu> menus, List<Menu> permissionMenus) {
        if (CollectionUtil.isEmpty(menus)) {
            return;
        }
        for (int i = 0; i < menus.size(); i++) {
            Menu menu = menus.get(i);
            boolean existed = false;
            for (Menu permissionMenu : permissionMenus) {
                if (menu.equals(permissionMenu)) {
                    existed = true;
                    break;
                }
            }
            if (!existed) {
                List<Menu> children = menu.getChildren();
                if (CollectionUtil.isNotEmpty(children)) {
                    removeUnPermission(children, permissionMenus);
                    i--;
                } else {
                    boolean contains = false;
                    if (CollectionUtil.isNotEmpty(permissionMenus)) {
                        for (Menu permissionMenu : permissionMenus) {
                            if (menu.equals(permissionMenu)) {
                                contains = true;
                                break;
                            }
                        }
                    }
                    if (!contains) {
                        menus.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    @Override
    public List<Menu> permissionComponent(String menuPath, String username) {
        List<Menu> components = null;
        List<Menu> menus = menuDao.findAllByUrlAndType(menuPath, MenuTypeEnum.MENU.getKey());
        if (CollectionUtil.isNotEmpty(menus)) {
            for (Menu menu : menus) {
                List<Menu> componentItems = menuDao.findAllByParentAndType(menu.getParent(), MenuTypeEnum.COMPONENT.getKey());
                components.addAll(componentItems);
            }
        }
        return components;
    }

    @Override
    public Menu save(Menu menu) {
        Menu saved = baseDao.findFirstByNameAndParent(menu.getName(), menu.getParent());
        if (saved != null) {
            if (menu.getId() == null || !menu.getId().equals(saved.getId())) {
                throw new AppException(RbacError.MENU_EXISTED);
            }
        }
        return super.save(menu);
    }
}
