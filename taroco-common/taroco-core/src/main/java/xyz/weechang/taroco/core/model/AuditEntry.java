package xyz.weechang.taroco.core.model;

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
@AllArgsConstructor
public class AuditEntry implements Serializable{

    private static final long serialVersionUID = 5556070338170508437L;

    private String who;
    private Date when;

    public AuditEntry(String who) {
        this(who, new Date());
    }

}
