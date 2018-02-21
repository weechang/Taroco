package xyz.weechang.user.center.command.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.command.command.DeleteCommand;
import xyz.weechang.taroco.core.common.controller.BaseController;
import xyz.weechang.taroco.core.common.exception.BusinessException;
import xyz.weechang.taroco.core.common.response.BaseResponse;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.command.command.UserCreateCommand;
import xyz.weechang.user.center.command.command.UserUpdateCommand;
import xyz.weechang.user.center.command.dto.UserCreateRequest;
import xyz.weechang.user.center.command.dto.UserUpdateRequest;
import xyz.weechang.user.center.error.UCError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 23:00.
 */
@Slf4j
@Api(tags = "user", description = "用户管理")
@RestController
@RequestMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController{

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("创建用户")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse create(@RequestBody UserCreateRequest request){
        log.debug(UserCreateRequest.class.getSimpleName() + " request received");

        UserCreateCommand command = new UserCreateCommand(createAudit(), request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }
        log.debug(UserCreateCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());

        return BaseResponse.create();
    }

    @ApiOperation("修改用户")
    @PatchMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestBody UserUpdateRequest request){
        log.debug(UserUpdateRequest.class.getSimpleName() + " request received");

        UserUpdateCommand command = new UserUpdateCommand(createAudit(), id, request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(OrgUpdateCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse delete(@PathVariable String id, Boolean logic){
        log.debug("delete request received");

        DeleteCommand command = new DeleteCommand(createAudit(), id, logic);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(DeleteCommand.class.getSimpleName() + " sent to command gateway: user [{}] ", command.getId());
        return BaseResponse.create();
    }
}
