package cn.ikangjia.yumi.dms.api.dto.table;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class TableCreateDTO {
    private Long datasourceId;
    private String databaseName;

    private String tableName;
    private String comment;

    private List<ColumnCreateDTO> columnCreateDTOList;
}
