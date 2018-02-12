package xyz.weechang.taroco.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:29.
 */
@Data
public class BaseEntry implements Serializable {

    private static final long serialVersionUID = 5966306766659220492L;

    @Id
    protected String id;

    protected Date createdDate = new Date();

    protected String createdBy;

    protected Date updatedDate = new Date();

    protected String updatedBy;

    protected Boolean deleted = false;
}
