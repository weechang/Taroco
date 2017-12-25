package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.base.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.base.model.AuditEntry;
import xyz.weechang.user.center.command.dto.UserUpdateRequest;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:51.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserUpdateCommand extends AuditAbleAbstractCommand {
    private static final long serialVersionUID = 8061749033349384760L;

    @TargetAggregateIdentifier
    private String id;

    private String password;

    private String phone;

    private String email;

    private Boolean enable;

    public UserUpdateCommand(AuditEntry auditEntry, String id, UserUpdateRequest request) {
        new UserUpdateCommand(auditEntry, id, request.getPassword(),
                request.getPhone(), request.getEmail());
    }

    public UserUpdateCommand(
            AuditEntry auditEntry, String id, String password, String phone, String email) {
        super(auditEntry);
        new UserUpdateCommand(id, password, phone, email, true);
    }
}
