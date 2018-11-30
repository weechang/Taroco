package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.AppDao;
import io.github.weechang.moreco.monitor.manager.model.domain.App;
import io.github.weechang.moreco.monitor.manager.service.AppService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:34
 */
@Service
public class AppServiceImpl extends BaseServiceImpl<AppDao, App> implements AppService {
}
