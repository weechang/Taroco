package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.ProjectDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Project;
import io.github.weechang.moreco.monitor.manager.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/27
 * time 14:27
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectDao, Project> implements ProjectService {
}
