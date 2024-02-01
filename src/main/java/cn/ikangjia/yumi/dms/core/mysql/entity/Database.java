package cn.ikangjia.yumi.dms.core.mysql.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 16:54
 */
@Data
public class Database {
    private String dbName;
    private String collate;
    private String charset;

}
