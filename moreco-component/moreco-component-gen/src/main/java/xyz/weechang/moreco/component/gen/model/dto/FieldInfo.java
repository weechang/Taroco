package xyz.weechang.moreco.component.gen.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author zhangwei
 * date 2019/2/28
 * time 15:26
 */
@ApiModel("字段")
@Data
public class FieldInfo implements Serializable {
    private static final long serialVersionUID = 2162499045867364588L;

    @ApiModelProperty("字段")
    private String fieldName;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("java类型")
    private String javaType;

    @ApiModelProperty("必须")
    private boolean required;

    @ApiModelProperty("隐藏")
    private boolean hidden;

    @ApiModelProperty("最大长度")
    private int length;

    @ApiModelProperty("新增")
    private boolean insertAble;

    @ApiModelProperty("修改")
    private boolean updateAble;

    public FieldInfo(Field field) {
        this.fieldName = field.getName();
        this.javaType = field.getType().getSimpleName();
        this.insertAble = true;
        this.updateAble = true;

        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        if (apiModelProperty != null) {
            this.desc = apiModelProperty.value();
            this.required = apiModelProperty.required();
            this.hidden = apiModelProperty.hidden();
        }

        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            this.length = column.length();
            this.required = this.required || (!column.nullable());
            this.insertAble = column.insertable();
            this.updateAble = column.updatable();
        }
    }
}
