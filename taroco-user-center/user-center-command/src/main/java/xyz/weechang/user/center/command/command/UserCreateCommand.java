package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.model.AuditEntry;
import xyz.weechang.user.center.command.dto.UserCreateRequest;

import java.util.UUID;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:51.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserCreateCommand extends AuditAbleAbstractCommand {
    private static final long serialVersionUID = 7454506146805405398L;

    @TargetAggregateIdentifier
    private String id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Boolean enable;

    public UserCreateCommand(AuditEntry auditEntry, UserCreateRequest request) {
        new UserCreateCommand(auditEntry, request.getUsername(),
                request.getPassword(), request.getPhone(), request.getEmail());
    }

    public UserCreateCommand(
            AuditEntry auditEntry, String username,
            String password, String phone, String email) {
        super(auditEntry);
        new UserCreateCommand(UUID.randomUUID().toString(), username, password, phone, email, true);
    }
}
