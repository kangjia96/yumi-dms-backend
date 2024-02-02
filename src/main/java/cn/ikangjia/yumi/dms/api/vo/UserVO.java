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
    private String name;
    private String account;
    private String password;
    private String avatar;
    private String phone;
    private String email;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /*
     * 是否启用，1-启用，2-禁用
     */
    private boolean enabled;

    private boolean deleted;

    private String token;
}
