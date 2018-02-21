package xyz.weechang.taroco.core.query.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 审计实体
 *
 * @author weechang
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AuditEntry implements Serializable{

    private static final long serialVersionUID = 5556070338170508437L;

    private String who;
    private Date when;

    public AuditEntry(String who) {
       new AuditEntry(who, new Date());
    }

    public AuditEntry(String who, Date when){
        this.who = who;
        this.when = when;
    }

}
