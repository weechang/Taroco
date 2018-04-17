package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.Set;

@Data
@EqualsAndHashCode(of = "username", callSuper = false)
@ToString(of = "username", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntry {

    public static final String           NAME_REGEX = "^[A-Za-z0-9_]*$";

    private String                       username;

    private String                       password;

    private boolean                      disabled;

    private Set<UserRoleXrefEntity>      roles;

    private Set<UserAuthorityXrefEntity> authorities;

}
