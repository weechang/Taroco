package xyz.weechang.moreco.core.generator.dto;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * date 2019/2/28
 * time 15:25
 */
@ApiModel("类信息")
@Data
public class ClassInfo implements Serializable {
    private static final long serialVersionUID = 5025626987703970923L;

    @ApiModelProperty("类名")
    private String clazzName;

    @ApiModelProperty("简写类名")
    private String clazzSimpleName;

    @ApiModelProperty("小写简写类名")
    private String lowerClazzSimpleName;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("字段")
    private List<FieldInfo> fieldInfos;

    public ClassInfo(String clazzName) {
        this.clazzName = clazzName;
        this.fieldInfos = new ArrayList<>();
        try {
            Class clazz = Class.forName(clazzName);
            this.clazzSimpleName = clazz.getSimpleName();
            this.lowerClazzSimpleName = (new StringBuilder()).append(Character.toLowerCase(clazzSimpleName.charAt(0))).append(clazzSimpleName.substring(1)).toString();
            ApiModel apiModel = (ApiModel) clazz.getAnnotation(ApiModel.class);
            if (apiModel != null && StrUtil.isNotBlank(apiModel.value())) {
                this.desc = apiModel.value();
            }
            Field[] fields = clazz.getFields();
            for (Field field : fields) {
                FieldInfo fieldInfo = new FieldInfo(field);
                fieldInfos.add(fieldInfo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
