package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.base.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.base.model.AuditEntry;

import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/12/18 10:52.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class LoginCommand extends AuditAbleAbstractCommand {

    private static final long serialVersionUID = 1599172985247953127L;

    @TargetAggregateIdentifier
    private String id;

    private String username;

    private String password;

    public LoginCommand(AuditEntry auditEntry, String username, String password) {
        super(auditEntry);
        new LoginCommand(UUID.randomUUID().toString(), username, password);
    }
}
