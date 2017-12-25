package xyz.weechang.user.center.command.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.base.controller.BaseController;
import xyz.weechang.taroco.core.response.BaseResponse;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:59.
 */
@Slf4j
@Api("角色管理")
@RequestMapping
@RestController
public class RoleController extends BaseController{

    @ApiOperation("创建角色")
    @PostMapping
    public BaseResponse create(){
        return BaseResponse.create();
    }

    @ApiOperation("修改角色")
    @PatchMapping
    public BaseResponse update(){
        return BaseResponse.create();
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    public BaseResponse delete(){
        return BaseResponse.create();
    }

    @ApiOperation("角色授权")
    @PostMapping("auth")
    public BaseResponse auth(){
        return BaseResponse.create();
    }
}
