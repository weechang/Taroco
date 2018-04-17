package xyz.taroco.oauth2.domain;

import lombok.*;
import xyz.weechang.taroco.core.query.domain.BaseEntry;

@Data
@EqualsAndHashCode(of = {"clientDetails", "resourceId"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDetailsToResourceIdXrefEntity extends BaseEntry {

    private ClientDetailsEntity clientDetails;

    private ResourceIdEntity resourceId;

}
