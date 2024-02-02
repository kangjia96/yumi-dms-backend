package cn.ikangjia.yumi.dms.api.dto.table;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class RenameDTO {
    private Long datasourceId;
    private String databaseName;
    private String tableName;
    private String oldTableName;
}
