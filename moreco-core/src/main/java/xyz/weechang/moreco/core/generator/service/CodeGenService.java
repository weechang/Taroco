package xyz.weechang.moreco.core.generator.service;


import xyz.weechang.moreco.core.generator.dto.CodeGenRequest;
import xyz.weechang.moreco.core.generator.dto.EntityInfo;

import java.util.List;

/**
 * @author zhangwei
 * date 2019/2/27
 * time 14:20
 */
public interface CodeGenService {

    /**
     * 获取扫描到的实体类列表
     *
     * @return 实体类列表
     */
    List<EntityInfo> getEntities();

    /**
     * 根据实体类生成代码
     *
     * @param codeGenRequest 实体生成请求
     * @return 生成的代码
     */
    byte[] generatorCode(CodeGenRequest codeGenRequest);
}
