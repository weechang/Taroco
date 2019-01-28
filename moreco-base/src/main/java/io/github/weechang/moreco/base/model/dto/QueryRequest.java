package io.github.weechang.moreco.base.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/1/28
 * time 17:11
 */
@Data
@ApiModel("查询参数")
public class QueryRequest implements Serializable {
    private static final long serialVersionUID = -4322863885794124909L;

    @ApiModelProperty("当前页码")
    private int currentPage;

    @ApiModelProperty("分页")
    private int pageSize;

    public PageModel toPage(){
       PageModel pageModel = new PageModel(currentPage, pageSize);
       return pageModel;
    }

    public PageRequest toPageRequest(){
        PageModel pageModel = new PageModel(currentPage, pageSize);
        return pageModel.toPageRequest();
    }
}
