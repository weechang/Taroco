package xyz.weechang.user.center.command.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.command.DeleteCommand;
import xyz.weechang.taroco.core.controller.BaseController;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.taroco.core.response.BaseResponse;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.command.dto.OrgCreateRequest;
import xyz.weechang.user.center.command.dto.OrgUpdateRequest;
import xyz.weechang.user.center.common.error.UCError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:11.
 */
@Slf4j
@Api("组织机构")
@RestController
@RequestMapping("org")
public class OrgController extends BaseController {

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("创建组织机构")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse create(@RequestBody OrgCreateRequest request) {
        log.debug(OrgCreateRequest.class.getSimpleName() + " request received");

        OrgCreateCommand command = new OrgCreateCommand(createAudit(), request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(OrgCreateRequest.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("修改组织机构")
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestBody OrgUpdateRequest request) {
        log.debug(OrgUpdateRequest.class.getSimpleName() + " request received");

        OrgUpdateCommand command = new OrgUpdateCommand(createAudit(), id, request);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(OrgUpdateCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("删除组织机构")
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, Boolean logic) {
        log.debug("delete request received");

        DeleteCommand command = new DeleteCommand(createAudit(), id, logic);
        UCError error = commandGateway.sendAndWait(command);
        if (error != null) {
            throw new BusinessException(error);
        }

        log.debug(DeleteCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }
}
