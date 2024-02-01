package cn.ikangjia.yumi.dms.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 13:38
 */
@Data
@TableName("t_user")
public class UserDO {
    @TableId
    private Long id;
    private String nickname;
    private String account;
    private String password;
    private String phone;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private boolean deleted;
}
