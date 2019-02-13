package io.github.moreco.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author zhangwei
 * date 2018/12/21
 * time 13:49
 */
@NoArgsConstructor
@Data
public class BufferPool implements Serializable {

    private static final long serialVersionUID = -8280947188382489481L;
    private Long totalCapacity;
    private Long memoryUsed;
    private Long count;
    private String poolName;
}
