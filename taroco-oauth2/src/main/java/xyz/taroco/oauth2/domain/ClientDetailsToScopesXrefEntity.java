package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = {"clientDetails", "scope"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDetailsToScopesXrefEntity extends BaseEntry {

    private Boolean autoApprove;

    private ClientDetailsEntity clientDetails;

    private ScopeEntity scope;

}
