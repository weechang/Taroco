package xyz.taroco.oauth2.domain;

import lombok.*;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.Set;

@Data
@EqualsAndHashCode(of = "tokenId", callSuper = false)
@ToString(of = "tokenId", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenEntity extends BaseEntry {

    private String tokenId;

    private OAuth2RefreshToken token;

    private OAuth2Authentication authentication;

    private Set<AccessTokenEntity> accessTokens;

}
