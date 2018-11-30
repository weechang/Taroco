package io.github.weechang.moreco.monitor.manager.controller;

import io.github.weechang.moreco.monitor.manager.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2018/11/27
 * time 14:29
 */
@Api(tags = "project", description = "项目管理")
@RequestMapping("monitor/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;
}
