package cn.ikangjia.yumi.dms.domain.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/12/17 15:16
 */
@Data
public class UserDO {
    private Long id;
    private String username;
    private String password;
    private String email;
    
}
