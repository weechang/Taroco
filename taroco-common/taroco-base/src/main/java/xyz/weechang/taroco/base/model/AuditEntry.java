package xyz.weechang.taroco.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 审计实体
 *
 * @author weechang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntry implements Serializable{

    private static final long serialVersionUID = 5556070338170508437L;

    private String who;
    private Date when;

    public AuditEntry(String who) {
        this(who, new Date());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
