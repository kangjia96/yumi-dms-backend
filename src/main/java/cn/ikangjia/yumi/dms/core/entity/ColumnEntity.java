package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class ColumnEntity {
    private String tableName;
    private String columnName;

    private String typeInfo;

    // 默认值
    private String defaultValue;

    // 注释
    private String comment;

    // 是否允许是空
    private boolean isNull;

    // 是否唯一
    private boolean unique;

    // 是否自增
    private boolean increment;

    // 是否是主键
    private boolean primaryKey;
}
