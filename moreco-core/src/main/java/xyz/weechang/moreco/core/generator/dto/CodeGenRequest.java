package xyz.weechang.moreco.core.generator.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangwei
 * date 2019/2/27
 * time 14:23
 */
@ApiModel("代码生成请求")
@Data
public class CodeGenRequest implements Serializable {

    private static final long serialVersionUID = 3832640328009549177L;

    @ApiModelProperty("包名")
    private String packageName;

    @ApiModelProperty("模块名")
    private String moduleName;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("作者名")
    private String email;

    @ApiModelProperty("需要生成代码的类名")
    private List<String> classes;
}
