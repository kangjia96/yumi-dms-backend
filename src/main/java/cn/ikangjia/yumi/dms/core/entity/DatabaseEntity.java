package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DatabaseEntity {
    private String databaseName;
    private String collation;
    private String characterSet;

    private String sqlPath;

    private String version;

    private String databaseDDL;


    private String tableCount;
    private String viewCount;
    private String procedureCount;
    private String functionCount;
}
