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
@TableName("t_datasource")
public class DatasourceDO {
    private long id;
    private String name;
    private String type;

    private String host;
    private String port;
    private String username;
    private String password;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /*
     * 是否启用，1-启用，2-禁用
     */
    private boolean enabled;

    /*
     * 是否逻辑删除，1-删除，0-未删除
     */
    @TableField("is_deleted")
    private boolean deleted;
}
