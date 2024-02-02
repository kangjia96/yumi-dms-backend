package cn.ikangjia.yumi.dms.api.dto.sqlconsole;

import lombok.Data;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class ExecuteDTO {
    private Long datasourceId;
    private String databaseName;
    private String sqlStr;

}
