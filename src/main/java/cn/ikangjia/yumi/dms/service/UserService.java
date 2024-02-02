package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.dto.system.UserLoginDTO;
import cn.ikangjia.yumi.dms.api.vo.UserVO;
import cn.ikangjia.yumi.dms.domain.entity.UserDO;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface UserService {

    UserDO getUser(Long id);

    UserVO doLogin(UserLoginDTO userLoginDTO);
}
