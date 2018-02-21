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
import xyz.weechang.user.center.command.command.MenuCreateCommand;
import xyz.weechang.user.center.command.command.MenuUpdateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.command.dto.MenuCreateRequest;
import xyz.weechang.user.center.command.dto.MenuUpdateRequest;
import xyz.weechang.user.center.error.UCError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:59.
 */
@Slf4j
@Api(tags = {"menu"}, description = "目录管理")
@RestController
@RequestMapping(value = "menu", consumes = MediaType.APPLICATION_JSON_VALUE)
public class MenuController extends BaseController{

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("创建目录")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse create(@RequestBody MenuCreateRequest request){
        log.debug(MenuCreateRequest.class.getSimpleName() + " request received");

        MenuCreateCommand command = new MenuCreateCommand(createAudit(), request);
        commandGateway.send(command);
        log.debug(OrgUpdateCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());

        return BaseResponse.create();
    }

    @ApiOperation("修改目录")
    @PatchMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestBody MenuUpdateRequest request){
        log.debug(MenuUpdateRequest.class.getSimpleName() + " request received");

        MenuUpdateCommand command = new MenuUpdateCommand(createAudit(), id, request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(OrgUpdateCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("删除目录")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse delete(@PathVariable String id, Boolean logic){
        log.debug("delete request received");

        DeleteCommand command = new DeleteCommand(createAudit(), id, logic);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(DeleteCommand.class.getSimpleName() + " sent to command gateway: menu [{}] ", command.getId());
        return BaseResponse.create();
    }
}
