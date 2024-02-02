package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.dto.table.RenameDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableCreateDTO;
import cn.ikangjia.yumi.dms.api.query.DataQuery;
import cn.ikangjia.yumi.dms.api.vo.TableInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.api.vo.data.DataShowVO;
import cn.ikangjia.yumi.dms.util.TreeUtil;
import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import cn.ikangjia.yumi.dms.core.sqlbuilder.table.TableSQL;
import cn.ikangjia.yumi.dms.manager.MySQLManager;
import cn.ikangjia.yumi.dms.manager.TableManager;
import cn.ikangjia.yumi.dms.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Service
public class TableServiceImpl implements TableService {

    private final MySQLManager mySQLManager;
    private final TableManager tableManager;

    public TableServiceImpl(MySQLManager mySQLManager, TableManager tableManager) {
        this.mySQLManager = mySQLManager;
        this.tableManager = tableManager;
    }

    @Override
    public List<TreeVO> listTree2Table(Long datasourceId, String databaseName) {
        // 查询出指定数据源下的指定数据库下的所有的表名称列表
        List<String> tableNameList = mySQLManager.listTable(datasourceId, databaseName);
        // 构造树形数据返并回
        return TreeUtil.buildLevel2(datasourceId, databaseName, tableNameList, 0);
    }

    @Override
    public DataShowVO showData(Long datasourceId, DataQuery dataQuery) {
        DataEntity dataEntity = mySQLManager.showTableData(datasourceId, dataQuery);
        Long total = mySQLManager.countTableDataRows(
                dataQuery.getDatasourceId(),
                dataQuery.getDatabaseName(),
                dataQuery.getTableName()
        );

        DataShowVO result = new DataShowVO();
        result.setDataEntity(dataEntity);
        result.setTotal(total);
        return result;
    }

    @Override
    public String createTable(TableCreateDTO tableCreateDTO) {
        Long datasourceId = tableCreateDTO.getDatasourceId();
        tableManager.createTable(datasourceId, tableCreateDTO);

        return tableCreateDTO.getTableName();
    }

    @Override
    public List<String> listDataTypes() {
        List<TableSQL.DataTypeEnum> allDataTypes = TableSQL.DataTypeEnum.getAllDataTypes();
        return allDataTypes.stream().map(TableSQL.DataTypeEnum::getDataType).toList();
    }

    @Override
    public Boolean clearTable(TableDTO tableDTO) {
        return tableManager.clearTable(tableDTO.getDatasourceId(), tableDTO.getDatabaseName(), tableDTO.getTableName());
    }

    @Override
    public Boolean truncateTable(TableDTO tableDTO) {
        return tableManager.truncateTable(tableDTO.getDatasourceId(), tableDTO.getDatabaseName(), tableDTO.getTableName());
    }

    @Override
    public Boolean dropTable(TableDTO tableDTO) {
        return tableManager.dropTable(tableDTO.getDatasourceId(), tableDTO.getDatabaseName(), tableDTO.getTableName());
    }

    @Override
    public Boolean renameTable(RenameDTO renameDTO) {
        return tableManager.renameTable(renameDTO.getDatasourceId(), renameDTO);
    }

    @Override
    public TableInfoVO getTableInfo(TableDTO tableDTO) {
        return tableManager.getTableInfo(
                tableDTO.getDatasourceId(),
                tableDTO.getDatabaseName(),
                tableDTO.getTableName());
    }
}
