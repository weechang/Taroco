package xyz.weechang.user.center.command.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.common.controller.BaseController;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.taroco.core.common.response.BaseResponse;
import xyz.weechang.user.center.command.command.RoleCreateCommand;
import xyz.weechang.user.center.command.command.RoleDeleteCommand;
import xyz.weechang.user.center.command.command.RoleUpdateCommand;
import xyz.weechang.user.center.command.dto.RoleCreateRequest;
import xyz.weechang.user.center.command.dto.RoleUpdateRequest;
import xyz.weechang.user.center.error.UCError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:59.
 */
@Slf4j
@Api(tags = "role", description = "角色管理")
@RequestMapping("role")
@RestController
public class RoleController extends BaseController{

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("创建角色")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse create(@RequestBody RoleCreateRequest request){
        log.debug(RoleCreateRequest.class.getSimpleName() + " request received");

        RoleCreateCommand command = new RoleCreateCommand(createAudit(), request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }
        log.debug(RoleCreateCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());

        return BaseResponse.create();
    }

    @ApiOperation("修改角色")
    @PatchMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestBody RoleUpdateRequest request) {
        log.debug(RoleUpdateRequest.class.getSimpleName() + " request received");

        RoleUpdateCommand command = new RoleUpdateCommand(createAudit(), id, request);
        commandGateway.send(command);
        log.debug(RoleUpdateCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestParam Boolean logic) {
        log.debug("delete request received");

        RoleDeleteCommand command = new RoleDeleteCommand(createAudit(), id, logic);
        commandGateway.sendAndWait(command);
        log.debug(RoleDeleteCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }
}
