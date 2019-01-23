package io.github.weechang.moreco.monitor.sdk.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.management.MemoryType;

/**
 * @author zhangwei
 * date 2018/12/21
 * time 13:53
 */
@Slf4j
@Data
@NoArgsConstructor
public class MemoryPoolInfo {

    private String memoryPoolName;
    private MemoryType type;
    private long init;
    private long used;
    private long committed;
    private long max;
    private MemoryPoolInfo lastMemoryPoolInfo;
}
