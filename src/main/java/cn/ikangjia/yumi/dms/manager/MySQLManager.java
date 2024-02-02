package cn.ikangjia.yumi.dms.manager;

import cn.ikangjia.yumi.dms.api.query.DataQuery;
import cn.ikangjia.yumi.dms.core.entity.CharacterSetEntity;
import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import cn.ikangjia.yumi.dms.core.entity.DatabaseEntity;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface MySQLManager {
    void createDatabase(long datasourceId, DatabaseEntity database);

    void dropDatabase(long datasourceId, String databaseName);

    void alterDatabase(long datasourceId, String databaseName, String characterSet, String collation);

    DatabaseEntity getDatabaseInfo(long datasourceId, String databaseName);

    List<String> listDatabase(long datasourceId, boolean systemDatabaseShow);

    List<String> listTable(long datasourceId, String databaseName);

    List<String> listView(long datasourceId, String databaseName);

    List<CharacterSetEntity> listCharacterSets(Long datasourceId);

    List<String> listCollations(Long datasourceId, String characterSet);

    DataEntity showTableData(Long datasourceId, DataQuery dataQuery);

    Long countTableDataRows(Long datasourceId, String databaseName, String tableName);

}
