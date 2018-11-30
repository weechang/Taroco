package io.github.weechang.moreco.monitor.manager.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * date 2018/11/29
 * time 15:43
 */
@Api(tags = "alarmRecord", description = "报警记录")
@RequestMapping("monitor/alarmRecord")
@RestController
public class AlarmRecordController {
}
