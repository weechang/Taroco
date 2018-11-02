package io.github.weechang.moreco.security.base.model.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author zhangwei
 * date 2018/11/1
 * time 14:52
 */
@ApiModel("session版session")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicUpdate()
@Where(clause = "yn = 1")
public class SecuritySessionSession extends BaseDomain {
    private static final long serialVersionUID = 5500332917599467501L;

    @ApiModelProperty("session_id")
    private String sessionId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("session内容")
    @Column(length = 2048)
    private String session;

    @ApiModelProperty("过期时间")
    private Date expireTime;
}
