package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.dto.UserLoginDTO;
import cn.ikangjia.yumi.dms.domain.entity.UserDO;
import cn.ikangjia.yumi.dms.domain.mapper.UserMapper;
import cn.ikangjia.yumi.dms.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 13:56
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public Page<UserDO> getUsers(String nickname, Integer pageNo, Integer pageSize) {
        Page<UserDO> page = new Page<>(pageNo, pageSize);
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(nickname)) {
            wrapper.like("nickname", nickname);
        }
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public UserDO login(UserLoginDTO loginDTO) {
        String account = loginDTO.getAccount();
        String password = loginDTO.getPassword();
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        UserDO userDO = userMapper.selectOne(wrapper);
        if (userDO != null && userDO.getPassword().equals(password)) {
            return userDO;
        }
        throw new RuntimeException("账号或密码错误");
    }
}
