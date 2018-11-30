package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.MethodDao;
import io.github.weechang.moreco.monitor.manager.model.domain.Method;
import io.github.weechang.moreco.monitor.manager.service.MethodService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:35
 */
@Service
public class MethodServiceImpl extends BaseServiceImpl<MethodDao, Method> implements MethodService {
}
