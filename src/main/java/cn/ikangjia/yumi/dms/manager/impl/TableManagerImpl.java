package cn.ikangjia.yumi.dms.manager.impl;

import cn.ikangjia.yumi.dms.api.dto.table.ColumnCreateDTO;
import cn.ikangjia.yumi.dms.api.dto.table.RenameDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableCreateDTO;
import cn.ikangjia.yumi.dms.api.vo.TableInfoVO;
import cn.ikangjia.yumi.dms.core.ExecuteHandler;
import cn.ikangjia.yumi.dms.core.entity.TableEntity;
import cn.ikangjia.yumi.dms.core.sqlbuilder.table.TableSQL;
import cn.ikangjia.yumi.dms.core.sqlbuilder.table.TableSQLBuilder;
import cn.ikangjia.yumi.dms.manager.TableManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Service
public class TableManagerImpl implements TableManager {
    private final ExecuteHandler handler;

    public TableManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public void createTable(long datasourceId, TableCreateDTO tableCreateDTO) {
        String databaseName = tableCreateDTO.getDatabaseName();
        String tableName = tableCreateDTO.getTableName();
        String comment = tableCreateDTO.getComment();

        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableName);
        tableEntity.setComment(comment);
        tableEntity.setDatabaseName(databaseName);

        List<ColumnCreateDTO> columnDTOList = tableCreateDTO.getColumnCreateDTOList();

        String sql = TableSQLBuilder.getCreateSQL(tableEntity, columnDTOList);
        handler.execute(sql);
    }

    @Override
    public Boolean clearTable(Long datasourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_clear, databaseName, tableName);
        handler.execute(sql);
        return true;
    }

    @Override
    public Boolean truncateTable(Long datasourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_truncate, databaseName, tableName);
        handler.execute(sql);
        return true;
    }

    @Override
    public Boolean dropTable(Long datasourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_drop, databaseName, tableName);
        handler.execute(sql);
        return true;
    }

    @Override
    public Boolean renameTable(Long datasourceId, RenameDTO renameDTO) {
        String sql = String.format(TableSQL.table_rename, renameDTO.getOldTableName(), renameDTO.getTableName());
        handler.execute("use "  + renameDTO.getDatabaseName());
        handler.execute(sql);
        return true;
    }

    @Override
    public TableInfoVO getTableInfo(Long datasourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_select_info, databaseName, tableName);

        List<TableEntity> tableEntityList = handler.executeQuery(TableEntity.class, sql);
        TableEntity tableEntity = tableEntityList.getFirst();

        TableInfoVO tableInfoVO = new TableInfoVO();
        BeanUtils.copyProperties(tableEntity, tableInfoVO);

        // ddl
        String sqlDDL = String.format(TableSQL.table_show_create, databaseName, tableName);
        String ddl = handler.executeQueryString(sqlDDL, "Create Table");
        tableInfoVO.setTableDDL(ddl);

        // 行数
        String total = handler.executeQueryString(
                String.format(TableSQL.table_Row_count, databaseName, tableName),
                "total");
        tableInfoVO.setRowNum(total);
        return tableInfoVO;
    }
}
