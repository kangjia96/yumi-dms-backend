package cn.ikangjia.yumi.dms.api.controller.system;

import cn.ikangjia.yumi.dms.api.dto.UserLoginDTO;
import cn.ikangjia.yumi.dms.domain.entity.UserDO;
import cn.ikangjia.yumi.dms.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 13:55
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDO> getUsers(String nickname, Integer pageNo, Integer pageSize) {
        return userService.getUsers(nickname, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public UserDO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public UserDO login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }
}
