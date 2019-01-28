package io.github.weechang.moreco.rbac.controller;

import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.model.dto.R;
import io.github.weechang.moreco.rbac.model.domain.Resource;
import io.github.weechang.moreco.rbac.model.dto.ResourceQueryRequest;
import io.github.weechang.moreco.rbac.model.dto.ResourceSaveRequest;
import io.github.weechang.moreco.rbac.service.ResourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:07
 */
@Api(tags = "resource", description = "资源管理")
@RequestMapping("rbac/resource")
@RestController
@CrossOrigin
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ApiOperation("分页获取数据资源")
    @GetMapping("page")
    public R<PageModel<Resource>> page(
            @ApiParam(name = "查询条件") ResourceQueryRequest queryRequest) {
        PageModel<Resource> page = resourceService.findAll(queryRequest.toResource(), queryRequest.toPageRequest());
        return R.ok(page);
    }

    @ApiOperation("获取详情")
    @GetMapping("/detail/{id}")
    public R<Resource> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Resource resource = resourceService.findOne(id);
        return R.ok(resource);
    }

    @ApiModelProperty("保存资源信息")
    @PostMapping("save")
    public R save(@RequestBody ResourceSaveRequest request) {
        Resource resource = request.toResource();
        resourceService.save(resource);
        return R.ok();
    }

    @ApiModelProperty("删除资源信息")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("资源id") @PathVariable("id") Long id) {
        resourceService.delete(id);
        return R.ok();
    }
}
