package io.github.weechang.moreco.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import io.github.weechang.moreco.base.exception.AppException;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.MenuDao;
import io.github.weechang.moreco.rbac.dao.ResourceDao;
import io.github.weechang.moreco.rbac.error.RbacError;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.service.MenuService;
import io.github.weechang.moreco.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:16
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public PageModel<Menu> findAll(Menu param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name" ,ExampleMatcher.GenericPropertyMatchers.contains());
        PageModel<Menu> page = new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
        return page;
    }

    @Override
    public List<Menu> tree() {
        List<Menu> list = baseDao.findAllByParentId(0L);
        buildTree(list);
        return list;
    }

    /**
     * 构建目录树
     *
     * @param menus 目录
     */
    private void buildTree(List<Menu> menus){
        if (CollectionUtil.isNotEmpty(menus)){
            for (Menu menu: menus){
                List<Menu> children = baseDao.findAllByParentId(menu.getId());
                menu.setChildren(children);
                buildTree(children);
            }
        }
    }

    @Override
    public Menu save(Menu menu) {
        long parentId = menu.getParentId() == null ? 0L : menu.getParentId();
        menu.setParentId(parentId);
        Menu saved = baseDao.findFirstByNameAndParentId(menu.getName(), menu.getParentId());
        if (saved != null) {
            if (menu.getId() == null || !menu.getId().equals(saved.getId())) {
                throw new AppException(RbacError.MENU_EXISTED);
            }
        }
        return super.save(menu);
    }
}
