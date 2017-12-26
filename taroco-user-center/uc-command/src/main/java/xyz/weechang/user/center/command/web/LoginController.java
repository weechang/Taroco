package xyz.weechang.user.center.command.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.controller.BaseController;
import xyz.weechang.user.center.command.command.LoginCommand;
import xyz.weechang.user.center.command.dto.LoginRequest;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/12/18 10:44.
 */
@Slf4j
@Api("用户登录")
@RestController
@RequestMapping("login")
public class LoginController extends BaseController{

    @Autowired(required = false)
    private CommandGateway commandGateway;

    @ApiOperation("用户登录")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void login(@RequestBody LoginRequest request, HttpServletResponse response, Principal principal) {
        log.debug(LoginRequest.class.getSimpleName() + " request received");

        LoginCommand command = new LoginCommand(createAudit(), request.getUsername(), request.getPassword());
        commandGateway.sendAndWait(command);
        log.debug(LoginCommand.class.getSimpleName() + " sent to command gateway: Blog Post [{}] ", command.getId());
    }

    @ApiOperation("用户登出")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void logout(HttpServletResponse response, Principal principal) {
        log.debug(LoginRequest.class.getSimpleName() + " request received");

//        LoginCommand command = new LoginCommand(createAudit(), request.getUsername(), request.getPassword());
//        commandGateway.sendAndWait(command);
//        log.debug(LoginCommand.class.getSimpleName() + " sent to command gateway: Blog Post [{}] ", command.getId());
    }
}
