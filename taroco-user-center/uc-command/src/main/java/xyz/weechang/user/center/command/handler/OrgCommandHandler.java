package xyz.weechang.user.center.command.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.weechang.taroco.core.command.DeleteCommand;
import xyz.weechang.taroco.core.exception.BusinessException;
import xyz.weechang.user.center.command.aggregate.Org;
import xyz.weechang.user.center.command.command.OrgCreateCommand;
import xyz.weechang.user.center.command.command.OrgUpdateCommand;
import xyz.weechang.user.center.common.error.UCError;
import xyz.weechang.user.center.query.dao.OrgDao;
import xyz.weechang.user.center.query.domain.OrgEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 17:32.
 */
@Slf4j
public class OrgCommandHandler {

    private Repository<Org> repository;
    private EventBus eventBus;

    public OrgCommandHandler(Repository<Org> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @Autowired
    private OrgDao orgDao;

    @CommandHandler
    public void handle(OrgCreateCommand command) throws Exception {
        OrgEntry saved = orgDao.findByCode(command.getCode());
        if (saved != null) {
            throw new BusinessException(UCError.ORG_CODE_IS_EXIST);
        }
        if (StringUtils.isNotEmpty(command.getParentId())) {
            OrgEntry parent = orgDao.findOne(command.getParentId());
            if (parent == null) {
                throw new BusinessException(UCError.ORG_CODE_IS_EXIST);
            }
        }
        repository.newInstance(() -> {
            return new Org(command);
        });
    }

    @CommandHandler
    public void handle(OrgUpdateCommand command) {
        OrgEntry saved = orgDao.findByCode(command.getCode());
        if (saved != null) {
            throw new BusinessException(UCError.ORG_CODE_IS_EXIST);
        }
        Aggregate<Org> org = repository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.update(command);
        });
    }

    @CommandHandler
    public void handle(DeleteCommand command) {
        Aggregate<Org> org = repository.load(command.getId());
        org.execute(aggregateRoot -> {
            aggregateRoot.delete(command);
        });
    }
}
