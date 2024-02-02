package cn.ikangjia.yumi.dms.api.query;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DataQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long datasourceId;
    private String databaseName;
    private String tableName;


    private Integer pageNum;
    private Integer pageSize;
}
