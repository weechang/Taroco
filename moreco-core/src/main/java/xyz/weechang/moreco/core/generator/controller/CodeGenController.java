package xyz.weechang.moreco.core.generator.controller;

import cn.hutool.core.io.IoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.weechang.moreco.core.generator.dto.CodeGenRequest;
import xyz.weechang.moreco.core.generator.dto.EntityInfo;
import xyz.weechang.moreco.core.generator.service.CodeGenService;
import xyz.weechang.moreco.core.model.dto.R;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 代码生成
 *
 * @author zhangwei
 * date 2019/2/27
 * time 14:17
 */
@Api(tags = "gen", description = "代码生成")
@RequestMapping("moreco/core/generator")
@RestController
public class CodeGenController {

    @Autowired
    private CodeGenService codeGenService;

    @ApiOperation("获取所有实体类")
    @GetMapping("list")
    public R<List<EntityInfo>> list() {
        List<EntityInfo> entities = codeGenService.getEntities();
        return R.ok(entities);
    }

    @ApiOperation("获取所有实体类")
    @PostMapping("generatorCode")
    public void generatorCode(@RequestBody CodeGenRequest request, HttpServletResponse response) throws IOException {
        byte[] data = codeGenService.generatorCode(request);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"moreco-code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), true, data);
    }

}
