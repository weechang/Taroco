package xyz.weechang.moreco.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangwei
 * date 2019/1/2
 * time 13:04
 */
@NoArgsConstructor
@Data
public class JvmProcess implements Serializable {

    private static final long serialVersionUID = -3096578014485935869L;

    private int pid;

    private String mainClass;

    private boolean current;

    public JvmProcess(int pid, String mainClass, boolean current){
        this.pid  = pid;
        this.mainClass = mainClass;
        this.current = current;
    }
}
