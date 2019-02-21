package xyz.weechang.moreco.component.rbac.dao;

import org.springframework.data.jpa.repository.Query;
import xyz.weechang.moreco.component.rbac.model.domain.Resource;
import xyz.weechang.moreco.data.jpa.JpaBaseRepository;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 22:54
 */
public interface ResourceDao extends JpaBaseRepository<Resource> {

    Resource findFirstByPath(String path);

    @Query(value = "select r.tag from Resource r where r.yn = 1 group by tag")
    List<String> findTagsGroupByTag();

    List<Resource> findAllByTag(String tag);
}
