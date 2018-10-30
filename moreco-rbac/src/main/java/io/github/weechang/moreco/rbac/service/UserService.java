package io.github.weechang.moreco.rbac.service;

import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.RbacUser;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:01
 */
public interface UserService extends BaseService<RbacUser> {

    /**
     * 根据用户id 查询已授权的所有目录id
     *
     * @param id 用户id
     * @return 已授权目录id
     */
    List<Long> findAllMenuIdByUserId(Long id);

    /**
     * 根据用户id 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePasswordByUserId(Long id, String newPassword);

    /**
     * 根据用户id 重置用户密码
     *
     * @param id 用户id
     */
    void resetPasswordByUserId(Long id);

    /**
     * 根据用户id 锁定账户
     *
     * @param userId 用户id
     */
    void changeLock(Long userId);
}
