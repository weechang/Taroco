package io.github.weechang.moreco.rbac.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import io.github.weechang.moreco.base.model.dto.PageModel;
import io.github.weechang.moreco.base.service.BaseService;
import io.github.weechang.moreco.rbac.model.domain.User;
import io.github.weechang.moreco.rbac.model.domain.enums.UserStatusEnum;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhangwei
 * date 2018/10/27
 * time 16:01
 */
public interface UserService extends BaseService<User> {

    /**
     * 属性转换
     *
     * @param users 用户
     */
    static void convert2String(List<User> users) {
        if (CollectionUtil.isNotEmpty(users)) {
            for (User user : users) {
                user.addDataMap("createdDate", DateUtil.formatDateTime(user.getCreatedDate()));
                user.addDataMap("status", UserStatusEnum.getNameByKey(user.getStatus()));
            }
        }
    }

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
