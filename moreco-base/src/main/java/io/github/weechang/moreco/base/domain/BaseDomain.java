package io.github.weechang.moreco.base.domain;

import com.google.common.collect.Maps;
import io.github.weechang.moreco.base.domain.enums.YnEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/20 13:29.
 */
@ApiModel
@Data
@MappedSuperclass
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 5966306766659220492L;

    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("创建时间")
    @CreatedDate
    protected Date createdDate;

    @ApiModelProperty("创建人")
    protected String createdBy;

    @ApiModelProperty("最后更新时间")
    @LastModifiedDate
    protected Date updatedDate;

    @ApiModelProperty("更新人")
    protected String updatedBy;

    @ApiModelProperty("是否删除 0-未删除，1-已删除")
    protected Integer yn = YnEnums.Y.getKey();

    @ApiModelProperty("扩展信息")
    @Transient
    protected Map<String, Object> dataMap = Maps.newHashMap();

    public void addDataMap(String key, Object value){
        dataMap.put(key, value);
    }
}
