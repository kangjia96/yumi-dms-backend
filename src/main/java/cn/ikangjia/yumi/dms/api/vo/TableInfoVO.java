package cn.ikangjia.yumi.dms.api.vo;

import lombok.Data;


/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class TableInfoVO {
    private String databaseName;
    private String tableName;
    private String tableType;
    private String dataLength;
    private String engine;
    private String createTime;
    private String updateTime;
    private String collation;
    private String comment;

    private String rowNum; // 数据行数
    private String tableDDL;
}
