package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:51.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Menu {

    @AggregateIdentifier
    private String id;

    private String parentId;

    private String name;

    private String uri;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private Boolean open;

}
