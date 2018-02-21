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
import xyz.weechang.taroco.core.common.response.BaseResponse;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.command.dto.OrgCreateRequest;
import xyz.weechang.user.center.command.dto.OrgUpdateRequest;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:11.
 */
@Slf4j
@Api(tags = "org", description = "组织机构管理")
@RestController
@RequestMapping(value = "org", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrgController extends BaseController {

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("创建组织机构")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public BaseResponse create(@RequestBody OrgCreateRequest request) {
        log.debug(OrgCreateRequest.class.getSimpleName() + " request received");

        OrgCreateCommand command = new OrgCreateCommand(createAudit(), request);
        commandGateway.send(command);
        log.debug(OrgCreateRequest.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("修改组织机构")
    @PatchMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, @RequestBody OrgUpdateRequest request) {
        log.debug(OrgUpdateRequest.class.getSimpleName() + " request received");

        OrgUpdateCommand command = new OrgUpdateCommand(createAudit(), id, request);
        commandGateway.send(command);
        log.debug(OrgUpdateCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }

    @ApiOperation("删除组织机构")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public BaseResponse update(@PathVariable String id, Boolean logic) {
        log.debug("delete request received");

        DeleteCommand command = new DeleteCommand(createAudit(), id, logic);
        commandGateway.sendAndWait(command);
        log.debug(DeleteCommand.class.getSimpleName() + " sent to command gateway: org [{}] ", command.getId());
        return BaseResponse.create();
    }
}
