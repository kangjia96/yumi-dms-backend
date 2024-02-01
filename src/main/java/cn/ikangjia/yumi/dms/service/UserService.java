package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.domain.entity.UserDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 13:56
 */
public interface UserService {
    UserDO getUser(Long id);

    Page<UserDO> getUsers(String nickname, Integer pageNo, Integer pageSize);
}
