package xyz.weechang.user.center.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import xyz.weechang.taroco.core.command.command.AuditAbleAbstractCommand;
import xyz.weechang.taroco.core.query.domain.AuditEntry;
import xyz.weechang.user.center.command.dto.UserUpdateRequest;

import java.util.List;

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

    private List<String> roles;

    private List<String> orgs;

    public UserUpdateCommand(AuditEntry auditEntry, String id, UserUpdateRequest request) {
       super(auditEntry);
       this.id = id;
       this.password = request.getPassword();
       this.phone = request.getPhone();
       this.email = request.getEmail();
       this.roles = request.getRoles();
       this.orgs = request.getOrgs();
    }
}
