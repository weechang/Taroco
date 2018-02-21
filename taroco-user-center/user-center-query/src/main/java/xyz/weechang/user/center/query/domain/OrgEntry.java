package xyz.weechang.user.center.query.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：组织机构
 *
 * @author zhangwei
 * @version 2017/11/4 13:44.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Document
public class OrgEntry extends BaseEntry {

    private static final long serialVersionUID = 2136457038173964553L;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum = 1;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 子结构
     */
    @DBRef
    private List<OrgEntry> children = new ArrayList<OrgEntry>();
}
