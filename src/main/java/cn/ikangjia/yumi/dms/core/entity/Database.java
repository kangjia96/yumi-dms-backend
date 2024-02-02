package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class Database {
    private String databaseName;
    private String collation;
    private String characterSet;

    private String sqlPath;
}
