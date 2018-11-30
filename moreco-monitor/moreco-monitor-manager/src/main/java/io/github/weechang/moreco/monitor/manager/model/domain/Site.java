package io.github.weechang.moreco.monitor.manager.model.domain;

import io.github.weechang.moreco.base.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 13:09
 */
@ApiModel("站点")
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "moreco_monitor_site")
@DynamicUpdate()
@Where(clause = "yn = 1")
public class Site extends BaseDomain {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("URL")
    private String url;

    @ApiModelProperty("站点类型")
    private Integer siteType;

    @ApiModelProperty("监控频率")
    private Integer interval;

    @ApiModelProperty("是否报警")
    private Integer alarmStatus;
}
