package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = {"role"}, callSuper = true)
@ToString(of = "role", callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleXrefEntity extends BaseEntry {

    private UserEntity user;

    private RoleEntity role;

}
