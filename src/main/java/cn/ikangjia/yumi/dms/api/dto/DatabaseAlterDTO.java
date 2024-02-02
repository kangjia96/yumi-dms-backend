package cn.ikangjia.yumi.dms.api.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DatabaseAlterDTO {
    private Long datasourceId;
    private String databaseName;
    private String characterSet;
    private String collation;
}
