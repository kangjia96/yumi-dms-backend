package cn.ikangjia.yumi.dms.manager;

import cn.ikangjia.yumi.dms.core.entity.SQLResultEntity;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
public interface SQLManager {
    List<SQLResultEntity> executeSQLBatch(Long datasourceId, String databaseName, List<String> sqlList);
}
