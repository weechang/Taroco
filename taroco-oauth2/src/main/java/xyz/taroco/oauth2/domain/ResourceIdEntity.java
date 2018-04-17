package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.Set;

@Data
@EqualsAndHashCode(of = "value", callSuper = false)
@ToString(of = "value", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceIdEntity extends BaseEntry {

    private String value;

    private Set<ClientDetailsToResourceIdXrefEntity> clientDetailsToResourceIdXrefs;
}
