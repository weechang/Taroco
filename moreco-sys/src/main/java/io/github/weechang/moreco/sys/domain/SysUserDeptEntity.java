package io.github.weechang.moreco.sys.domain;

import io.github.weechang.weechang.moreco.base.BaseEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangwei
 * date 2018/10/26
 * time 18:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDeptEntity extends BaseEntry {
    private static final long serialVersionUID = 1717440330547599478L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 部门id
     */
    private Long deptId;
}
