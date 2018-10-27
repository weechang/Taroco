package io.github.weechang.moreco.base.domain;

import io.github.weechang.moreco.base.domain.enums.YnEnums;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
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
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 5966306766659220492L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    protected Date createdDate;

    protected String createdBy;

    @LastModifiedDate
    protected Date updatedDate;

    protected String updatedBy;

    protected Integer yn = YnEnums.Y.getKey();
}
