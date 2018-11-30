package io.github.weechang.moreco.monitor.manager.service.impl;

import io.github.weechang.moreco.base.service.impl.BaseServiceImpl;
import io.github.weechang.moreco.monitor.manager.dao.AlarmRecordDao;
import io.github.weechang.moreco.monitor.manager.model.domain.AlarmRecord;
import io.github.weechang.moreco.monitor.manager.service.AlarmRecordService;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:34
 */
@Service
public class AlarmRecordServiceImpl extends BaseServiceImpl<AlarmRecordDao, AlarmRecord> implements AlarmRecordService {
}
