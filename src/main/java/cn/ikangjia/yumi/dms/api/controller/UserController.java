package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.dto.system.UserLoginDTO;
import cn.ikangjia.yumi.dms.api.vo.UserVO;
import cn.ikangjia.yumi.dms.domain.entity.UserDO;
import cn.ikangjia.yumi.dms.service.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public UserVO login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.doLogin(userLoginDTO);
    }
}
