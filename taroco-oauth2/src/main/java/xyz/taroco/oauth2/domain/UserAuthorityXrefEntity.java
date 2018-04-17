package xyz.taroco.oauth2.domain;

import lombok.*;
import org.hibernate.annotations.Where;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = "authority", callSuper = true)
@ToString(of = "authority", callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthorityXrefEntity extends BaseEntry {

    private UserEntity user;

    private AuthorityEntity authority;

}
