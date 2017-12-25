package xyz.weechang.user.center.command.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 22:56.
 */
@Slf4j
@Data
@NoArgsConstructor
@Aggregate
public class Token {

    @AggregateIdentifier
    private String id;

    private String token;

    private Date gmtExpire;

    private Date gmtModified;
}
