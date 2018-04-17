package xyz.taroco.oauth2.domain;

import lombok.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = "tokenId", callSuper = false)
@ToString(exclude = { "token", "authentication", "refreshToken" }, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessTokenEntity extends BaseEntry {

    private String tokenId;

    private OAuth2AccessToken token;

    private String authenticationId;

    private String userName;

    private String clientId;

    private OAuth2Authentication authentication;

    private RefreshTokenEntity   refreshToken;

}
