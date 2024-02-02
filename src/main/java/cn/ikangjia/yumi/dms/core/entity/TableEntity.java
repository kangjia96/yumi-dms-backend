package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class TableEntity {
    private String databaseName;
    private String tableName;
    private String tableType;
    private String dataLength;
    private String engine;
    private String createTime;
    private String updateTime;
    private String collation;
    private String comment;
}
