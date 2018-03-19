package xyz.weechang.user.center.query.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.taroco.core.common.controller.BaseController;
import xyz.weechang.taroco.core.common.response.BaseResponse;
import xyz.weechang.user.center.query.dao.OrgDao;
import xyz.weechang.user.center.query.domain.OrgEntry;
import xyz.weechang.user.center.query.dto.OrgFindRequest;

import java.util.List;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2018/2/22 13:45.
 */
@Slf4j
@Api(tags = {"org"}, description = "组织机构查询")
@RestController
@RequestMapping(value = "org", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class OrgController extends BaseController{

    @Autowired
    private OrgDao orgDao;

    @ApiOperation("根据id获取组织机构")
    @GetMapping(value = "/{id}")
    public BaseResponse findById(@PathVariable String id){
        OrgEntry org = orgDao.findOne(id);
        return BaseResponse.create();
    }

    @ApiOperation("根据id获取组织机构")
    @GetMapping
    public BaseResponse findAll(@RequestParam OrgFindRequest request){
        List<OrgEntry> orgEntries = orgDao.findAll();
        return BaseResponse.create();
    }
}
