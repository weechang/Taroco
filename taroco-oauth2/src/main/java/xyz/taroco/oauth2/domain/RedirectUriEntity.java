package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = {"clientDetails", "value"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RedirectUriEntity extends BaseEntry {

    private ClientDetailsEntity clientDetails;

    private String value;

}
