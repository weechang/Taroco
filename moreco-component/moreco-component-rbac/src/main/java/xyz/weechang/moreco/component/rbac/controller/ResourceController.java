package xyz.weechang.moreco.component.rbac.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.moreco.component.rbac.model.domain.Resource;
import xyz.weechang.moreco.component.rbac.model.dto.ResourceQueryRequest;
import xyz.weechang.moreco.component.rbac.model.dto.ResourceSaveRequest;
import xyz.weechang.moreco.component.rbac.service.ResourceService;
import xyz.weechang.moreco.core.model.dto.PageModel;
import xyz.weechang.moreco.core.model.dto.R;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/1/26
 * time 23:07
 */
@Api(tags = "resource", description = "资源管理")
@RequestMapping("moreco/component/rbac/resource")
@RestController
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

    @ApiOperation("查询标签树")
    @GetMapping("tagTree")
    public R<List<Resource>> tagTree(@ApiParam(name = "目录Id") Long menuId) {
        List<Resource> tags = resourceService.getResourceTags(menuId);
        return R.ok(tags);
    }

    @ApiOperation("获取详情")
    @GetMapping("detail/{id}")
    public R<Resource> detail(
            @ApiParam(name = "id") @PathVariable("id") Long id) {
        Resource resource = resourceService.findById(id);
        return R.ok(resource);
    }

    @ApiOperation("保存资源信息")
    @PostMapping("save")
    public R save(@RequestBody ResourceSaveRequest request) {
        Resource resource = request.toResource();
        resourceService.save(resource);
        return R.ok();
    }

    @ApiOperation("删除资源信息")
    @DeleteMapping("delete/{id}")
    public R delete(@ApiParam("资源id") @PathVariable("id") Long id) {
        resourceService.delete(id);
        return R.ok();
    }
}
