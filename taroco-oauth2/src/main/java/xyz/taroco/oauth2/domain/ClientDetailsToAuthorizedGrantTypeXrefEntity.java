package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = {"clientDetails", "grantType"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDetailsToAuthorizedGrantTypeXrefEntity extends BaseEntry {

    private ClientDetailsEntity clientDetails;

    private GrantTypeEntity grantType;

}
