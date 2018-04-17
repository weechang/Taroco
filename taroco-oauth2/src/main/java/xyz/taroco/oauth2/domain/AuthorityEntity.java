package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = "authorityName", callSuper = false)
@ToString(of = "authorityName", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityEntity extends BaseEntry {

    private String authorityName;

    private boolean disabled;

}
