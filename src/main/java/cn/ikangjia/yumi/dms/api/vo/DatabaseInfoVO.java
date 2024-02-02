package cn.ikangjia.yumi.dms.api.vo;

import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;
import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DatabaseInfoVO {
    private DatasourceDO datasourceDO;

    private String databaseName;
    private String collation;
    private String characterSet;

    private String version;

    private String databaseDDL;

    private String sqlPath;

    private String tableCount;
    private String viewCount;
    private String procedureCount;
    private String functionCount;
}
