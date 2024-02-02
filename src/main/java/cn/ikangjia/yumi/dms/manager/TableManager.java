package cn.ikangjia.yumi.dms.manager;

import cn.ikangjia.yumi.dms.api.dto.table.RenameDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableCreateDTO;
import cn.ikangjia.yumi.dms.api.vo.TableInfoVO;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface TableManager {
    void createTable(long datasourceId, TableCreateDTO tableCreateDTO);

    Boolean clearTable(Long datasourceId, String databaseName, String tableName);

    Boolean truncateTable(Long datasourceId, String databaseName, String tableName);

    Boolean dropTable(Long datasourceId, String databaseName, String tableName);

    Boolean renameTable(Long datasourceId, RenameDTO renameDTO);

    TableInfoVO getTableInfo(Long datasourceId, String databaseName, String tableName);
}
