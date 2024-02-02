package cn.ikangjia.yumi.dms.api.dto.system;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class UserLoginDTO {
    private String account;

    private String password;

    private String code;
}
