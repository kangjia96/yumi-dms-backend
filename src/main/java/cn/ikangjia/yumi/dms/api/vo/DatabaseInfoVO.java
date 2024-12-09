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
    private String collation; // 排序规则
    private String characterSet; // 字符集

    private String version; // 数据库版本

    private String databaseDDL; // 数据库DDL

    private String sqlPath; // 数据库DDL文件路径

    private String tableCount; // 表数量
    private String viewCount; // 视图数量
    private String procedureCount; // 存储过程数量
    private String functionCount; // 函数数量
}
