package xyz.weechang.moreco.component.gen.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/2/28
 * time 10:09
 */
@ApiModel("实体")
@Data
public class EntityInfo implements Serializable {

    private static final long serialVersionUID = 1639328586611981053L;

    @ApiModelProperty("类名")
    private String clazz;

    @ApiModelProperty("实体名")
    private String entityName;

}
