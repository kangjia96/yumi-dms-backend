package cn.ikangjia.yumi.dms.api.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 15:19
 */
@Data
public class UserLoginDTO {
    private String account;
    private String password;
}
