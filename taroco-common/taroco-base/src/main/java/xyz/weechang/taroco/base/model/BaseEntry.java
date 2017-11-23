package xyz.weechang.taroco.base.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:29.
 */
@Data
@MappedSuperclass
public class BaseEntry implements Serializable {

    private static final long serialVersionUID = 5966306766659220492L;

    @Id
    protected String id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedDate;

    protected String updatedBy;

    protected Boolean deleted = false;
}
