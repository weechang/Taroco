package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.Set;

@Data
@EqualsAndHashCode(of = "clientId", callSuper = false)
@ToString(exclude = "clientSecret", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDetailsEntity extends BaseEntry {

    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds;


    private Integer refreshTokenValiditySeconds;

    private Set<ClientDetailsToAuthorizedGrantTypeXrefEntity> authorizedGrantTypeXrefs;


    private Set<ClientDetailsToScopesXrefEntity> scopeXrefs;


    private Set<ClientDetailsToResourceIdXrefEntity> resourceIdXrefs;


    private Set<RedirectUriEntity> redirectUris;

    private ClientDetailsLimitEntity clientLimit;

}
