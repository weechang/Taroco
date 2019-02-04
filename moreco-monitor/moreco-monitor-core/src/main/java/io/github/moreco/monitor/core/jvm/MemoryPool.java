package io.github.moreco.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.management.MemoryType;

/**
 *
 * @author zhangwei
 * date 2018/12/21
 * time 13:53
 */
@Data
@NoArgsConstructor
public class MemoryPool implements Serializable {

    private static final long serialVersionUID = 2635624763370224956L;
    private String memoryPoolName;
    private MemoryType type;
    private long init;
    private long used;
    private long committed;
    private long max;
    private MemoryPool lastMemoryPoolInfo;
}
