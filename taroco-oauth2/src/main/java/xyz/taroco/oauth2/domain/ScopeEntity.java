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
public class ScopeEntity extends BaseEntry {

    private String value;

    private Set<ClientDetailsToScopesXrefEntity> clientDetailsToScopesXrefs;

}
