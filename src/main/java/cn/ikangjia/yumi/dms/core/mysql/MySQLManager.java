package cn.ikangjia.yumi.dms.core.mysql;

import cn.ikangjia.yumi.dms.core.mysql.entity.Database;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 16:51
 */
public interface MySQLManager {
    Integer testConnection(Long datasourceId);

    Integer createDB(Long datasourceId, Database database);

    Integer dropDB(Long datasourceId, String databaseName);

    List<String> showDB(Long datasourceId, boolean systemDbShow);

    Integer changeDB(Long datasourceId, Database database);
}
