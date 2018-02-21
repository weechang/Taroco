package xyz.weechang.user.center.query.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.common.controller.BaseController;
import xyz.weechang.taroco.core.common.response.BaseResponse;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/21 20:43.
 */
@Slf4j
@Api(tags = {"menu"}, description = "目录管理")
@RestController
@RequestMapping(value = "menu", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MenuController extends BaseController{

    @ApiOperation("根据id获取目录")
    @GetMapping(value = "/{id}")
    public BaseResponse get(@PathVariable String id){
        return BaseResponse.create();
    }
}
