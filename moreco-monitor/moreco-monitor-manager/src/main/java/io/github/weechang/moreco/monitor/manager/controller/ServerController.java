package io.github.weechang.moreco.monitor.manager.controller;

import io.github.weechang.moreco.base.model.PageModel;
import io.github.weechang.moreco.base.model.R;
import io.github.weechang.moreco.monitor.manager.model.domain.Server;
import io.github.weechang.moreco.monitor.manager.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:44
 */
@Api(tags = "server", description = "服务器管理")
@RequestMapping("monitor/server")
@RestController
public class ServerController {

    @Autowired
    private ServerService serverService;

    @ApiOperation("分页获取服务器数据")
    @GetMapping("page/{index}")
    public R<PageModel<Server>> page(
            @ApiParam(name = "页码") @PathVariable("index") int index,
            @ApiParam(name = "参数") Server server) {
        PageModel request = new PageModel(index);
        PageModel<Server> page = serverService.findAll(server, request.toPageRequest());
        return R.ok(page);
    }

}
