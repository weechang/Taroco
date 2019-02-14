package xyz.weechang.moreco.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jvm gc 信息
 *
 * @author zhangwei
 * date 2018/12/3
 * time 14:59
 */
@NoArgsConstructor
@Data
public class JvmGc implements Serializable {

    private static final long serialVersionUID = 7272293778412803625L;
    /**
     * GC名称
     */
    private String gcName;

    /**
     * GC次数
     */
    private long gcTotalCount;

    /**
     * GC占用时间
     */
    private long gcTotalTime;
}
