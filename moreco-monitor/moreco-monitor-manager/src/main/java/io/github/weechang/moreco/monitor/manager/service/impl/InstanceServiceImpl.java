package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.InstanceDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Instance;
import io.github.weechang.moreco.monitor.manager.service.InstanceService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:34
 */
@Service
public class InstanceServiceImpl extends BaseServiceImpl<InstanceDao, Instance> implements InstanceService {
}
