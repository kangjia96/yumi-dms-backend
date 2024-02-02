package cn.ikangjia.yumi.dms.api.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class UserVO {
    private Long id;
    private String nickname;
    private String account;
    private String password;
    private String phone;
    private String email;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private boolean deleted;

    private String token;
}
