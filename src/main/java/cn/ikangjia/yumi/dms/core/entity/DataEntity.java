package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DataEntity {
    private List<String> columnNameList;
    private List<Map<String, Object>> dataMapList;
}
