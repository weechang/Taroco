package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import xyz.weechang.taroco.core.command.DeleteCommand;
import xyz.weechang.taroco.core.event.DeleteEvent;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.common.event.OrgCreateEvent;
import xyz.weechang.user.center.common.event.OrgUpdateEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 22:22.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Org{

    @AggregateIdentifier
    private String id;

    private String parentId;

    private String code;

    private String name;

    private Integer orderNum;

    private Boolean logic;

    public Org(OrgCreateCommand command) {
        apply(new OrgCreateEvent(
                command.getId(), command.getAuditEntry(),
                command.getParentId(), command.getCode(), command.getName(), 1));
    }

    public void update(OrgUpdateCommand command) {
        if (this.logic != null){
            throw new BusinessException();
        }
        apply(new OrgUpdateEvent(
                command.getId(), command.getAuditEntry(), command.getCode(),
                command.getName(), command.getOrderNum()));
    }

    public void delete(DeleteCommand command) {
        apply(new DeleteEvent(command.getId(), command.getAuditEntry(), command.getLogic()));
    }

    @EventSourcingHandler
    public void on(OrgCreateEvent event) {
        this.id = event.getId();
        this.parentId = event.getParentId();
        this.code = event.getCode();
        this.name = event.getName();
        this.orderNum = event.getOrderNum();
    }

    @EventSourcingHandler
    public void on(OrgUpdateEvent event) {
        if (StringUtils.isNotEmpty(event.getCode())){
            this.code = event.getCode();
        }
        if (StringUtils.isNotEmpty(event.getName())){
            this.name = event.getName();
        }
        if (event.getOrderNum() != null){
            this.orderNum = event.getOrderNum();
        }
    }

    @EventSourcingHandler
    public void on(DeleteCommand event) {
        if (event.getLogic() == null || event.getLogic()){
            this.logic = Boolean.TRUE;
        } else {
            this.logic = Boolean.FALSE;
        }
    }
}
