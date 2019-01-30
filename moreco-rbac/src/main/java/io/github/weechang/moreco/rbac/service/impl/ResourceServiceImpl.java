package io.github.weechang.moreco.rbac.service.impl;

import com.google.common.collect.Lists;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.rbac.dao.ResourceDao;
import io.github.weechang.moreco.rbac.model.domain.Menu;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.github.weechang.moreco.rbac.model.domain.Role;
import io.github.weechang.moreco.rbac.service.ResourceService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 22:53
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceDao, Resource> implements ResourceService {

    @Override
    public PageModel<Resource> findAll(Resource param, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("path", ExampleMatcher.GenericPropertyMatchers.contains());
        return new PageModel<>(baseDao.findAll(Example.of(param, matcher), pageable));
    }

    @Override
    public Resource getResourceByPath(String path) {
        return baseDao.findFirstByPath(path);
    }

    @Override
    public List<Role> getRolesById(Long id) {
        List<Role> roles = Lists.newArrayList();
        Resource resource = baseDao.findOne(id);
        if (resource != null) {
            List<Menu> menus = resource.getMenus();
            for (Menu menu : menus) {
                roles.addAll(menu.getRoles());
            }
        }
        return roles;
    }
}
