package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.query.domain.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

/**
 * 系统配置信息
 *
 * @author zhangwei
 * date 2018/10/26
 * time 17:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysConfigEntity extends BaseEntry {
    private static final long serialVersionUID = 2655238963530024942L;

    @Id
    private Long id;

    private String paramKey;

    private String paramValue;

    private String remark;
}
