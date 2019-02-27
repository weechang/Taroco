package xyz.weechang.moreco.component.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.weechang.moreco.component.rbac.dao.MenuDao;
import xyz.weechang.moreco.component.rbac.dao.UserDao;
import xyz.weechang.moreco.component.rbac.error.RbacError;
import xyz.weechang.moreco.component.rbac.model.domain.Menu;
import xyz.weechang.moreco.component.rbac.model.domain.Resource;
import xyz.weechang.moreco.component.rbac.model.domain.Role;
import xyz.weechang.moreco.component.rbac.model.domain.User;
import xyz.weechang.moreco.component.rbac.model.domain.enums.MenuShowEnum;
import xyz.weechang.moreco.component.rbac.model.domain.enums.MenuTypeEnum;
import xyz.weechang.moreco.component.rbac.service.MenuService;
import xyz.weechang.moreco.core.exception.AppException;
import xyz.weechang.moreco.core.model.dto.PageModel;
import xyz.weechang.moreco.core.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void doConvertDataMap(Menu... menus) {
        for (Menu menu : menus) {
            menu.addDataMap("type", MenuTypeEnum.getNameByKey(menu.getType()));
            menu.addDataMap("showBoolean", MenuShowEnum.SHOW.getKey().equals(menu.getVisible()));
            if (CollectionUtil.isNotEmpty(menu.getResources())) {
                menu.addDataMap("resourceIds", menu.getResources().stream().map(Resource::getId).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public PageModel<Menu> findAll(Menu param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<Menu> page = new PageModel<>(menuDao.findAll(Example.of(param, matcher), pageable));
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (Menu menu : page.getList()) {
                // 避免序列化的时候懒加载查询
                menu.setChildren(null);
            }
        }
        return page;
    }

    @Override
    public List<Menu> tree() {
        List<Menu> list = baseDao.findAllByParent(new Menu(0L));
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
        removeComponent(tree);
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

    /**
     * 移除非目录组件
     *
     * @param menus 目录
     */
    private void removeComponent(List<Menu> menus) {
        if (CollectionUtil.isNotEmpty(menus)) {
            int size = menus.size();
            for (int i = 0; i < menus.size(); i++) {
                Menu menu = menus.get(i);
                if (CollectionUtil.isNotEmpty(menu.getChildren())) {
                    removeComponent(menu.getChildren());
                } else {
                    if (!MenuTypeEnum.MENU.getKey().equals(menu.getType())) {
                        menus.remove(i);
                        i--;
                    }
                }
                if (size == menus.size()) {
                    break;
                }
            }
        }
    }

    @Override
    public List<Menu> permissionComponent(List<String> matchedPathList, String username) {
        List<Menu> components = null;
        if (CollectionUtil.isNotEmpty(matchedPathList)) {
            Menu menu = getTargetMenuByPath(matchedPathList, new Menu(0L), 0);
            if (menu != null) {
                components = menuDao.findAllByParentAndType(menu, MenuTypeEnum.COMPONENT.getKey());
            }
        }
        return components;
    }

    private Menu getTargetMenuByPath(List<String> matchedPathList, Menu parent, int index) {
        Menu menu = null;
        if (CollectionUtil.isNotEmpty(matchedPathList)) {
            String matchedPath = matchedPathList.get(index);
            if (index != 0) {
                String lastPath = matchedPathList.get(index - 1);
                matchedPath = matchedPath.replace(lastPath, "");
            }
            menu = menuDao.findFirstByParentAndUrlAndType(parent, matchedPath, MenuTypeEnum.MENU.getKey());
            if (index != 0 && menu == null) {
                matchedPath = matchedPath.replace("/", "");
                menu = menuDao.findFirstByParentAndUrlAndType(parent, matchedPath, MenuTypeEnum.MENU.getKey());
            }
            if (index != matchedPathList.size() - 1) {
                menu = getTargetMenuByPath(matchedPathList, menu, ++index);
            }
        }
        return menu;
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
