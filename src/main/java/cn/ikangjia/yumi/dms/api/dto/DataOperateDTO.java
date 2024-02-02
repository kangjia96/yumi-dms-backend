package cn.ikangjia.yumi.dms.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DataOperateDTO {
    private Long datasourceId;
    private String databaseName;
    private String tableName;

    private Map<String, List<Map<String, Object>>> insertData;

    private Map<String, List<Map<String, Object>>> updateData;

    // 更新的数据的原始数据
    private Map<String, List<Map<String, Object>>> updateOriginData;

    private Map<String, List<Map<String, Object>>> deleteData;
}
