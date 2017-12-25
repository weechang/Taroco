package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.weechang.taroco.base.model.BaseEntry;

import javax.persistence.*;
import java.util.Date;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/16 15:06.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class TokenEntry extends BaseEntry {

    private static final long serialVersionUID = 987915253534545259L;

    /**
     * 用户信息
     */
    @OneToOne
    private UserEntry user;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtExpire;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtModified;
}
