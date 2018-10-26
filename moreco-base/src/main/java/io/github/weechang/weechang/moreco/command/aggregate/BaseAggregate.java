package io.github.weechang.weechang.moreco.command.aggregate;

import lombok.Data;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/19 21:44.
 */
@Data
public class BaseAggregate implements Serializable {

    private static final long serialVersionUID = 2048099700767274434L;

    protected Boolean deleted = false;
}
