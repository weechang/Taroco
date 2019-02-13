package xyz.weechang.moreco.component.rbac.service;

import xyz.weechang.moreco.component.rbac.model.domain.User;
import xyz.weechang.moreco.core.model.dto.PageModel;
import xyz.weechang.moreco.core.service.BaseService;
import org.springframework.data.domain.Pageable;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:01
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据条件分页查询
     *
     * @param param    查询参数
     * @param pageable 分页参数
     * @return 结果
     */
    PageModel<User> findAll(User param, Pageable pageable);

    /**
     * 根据用户id 修改密码
     *
     * @param username    用户名
     * @param newPassword 新密码
     */
    void updatePasswordByUsername(String username, String newPassword);

    /**
     * 根据用户id 重置用户密码
     *
     * @param id 用户id
     */
    void resetPasswordByUserId(Long id);

    /**
     * 根据用户id 更改用户状态
     *
     * @param userId 用户id
     * @param status 状态
     */
    void changeStatus(Long userId, int status);

    /**
     * 详情
     *
     * @param id id
     * @return 详情
     */
    User detail(Long id);
}
