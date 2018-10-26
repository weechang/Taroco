package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.base.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 数据字典
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictEntity extends BaseEntry {
    private static final long serialVersionUID = 3686785939888784107L;

    @Id
    private Long id;

    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 字典码
     */
    private String code;
    /**
     * 字典值
     */
    private String value;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remark;
}
