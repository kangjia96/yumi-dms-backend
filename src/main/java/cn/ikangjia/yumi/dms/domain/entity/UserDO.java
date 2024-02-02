package cn.ikangjia.yumi.dms.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
@TableName(value = "t_user", excludeProperty={"code"})
public class UserDO {
    private long id;
    private String nickname;
    private String account;
    private String password;
    private String code;

    private String phone;
    private String email;
    
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField("is_deleted")
    private boolean deleted;

}
