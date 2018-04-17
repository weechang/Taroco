package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

import java.util.Set;

@Data
@EqualsAndHashCode(of = "name", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RoleEntity extends BaseEntry {

    private String                       name;

    private boolean                      disabled;

    private Set<RoleAuthorityXrefEntity> authorities;

    private Set<UserRoleXrefEntity>      users;

}
