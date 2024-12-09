package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.dto.table.RenameDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableCreateDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableDTO;
import cn.ikangjia.yumi.dms.infra.rest.ResultVO;
import cn.ikangjia.yumi.dms.api.query.DataQuery;
import cn.ikangjia.yumi.dms.api.vo.TableInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.api.vo.data.DataShowVO;
import cn.ikangjia.yumi.dms.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/api/v1/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tree")
    public ResultVO<List<TreeVO>> listTables(Long datasourceId, String databaseName) {
        return Optional.ofNullable(tableService.listTree2Table(datasourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/tableInfo")
    public ResultVO<TableInfoVO> getTableInfo(TableDTO tableDTO) {
        return Optional.of(tableService.getTableInfo(tableDTO))
                .map(ResultVO::success)
                .get();
    }

    @DeleteMapping
    public ResultVO<Boolean> dropTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.dropTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }
//
//    @ApiOperation(value = "修改表：更难更难")
//    @PutMapping
//    public ResultVO<Void> alterTable(TableDTO tableDTO) {
//        return ResultVO.success(null);
//    }

    @PostMapping
    public ResultVO<String> createTable(@RequestBody TableCreateDTO tableCreateDTO) {
        return Optional.ofNullable(tableService.createTable(tableCreateDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/dataType")
    public ResultVO<List<String>> listDataTypes() {
        return Optional.ofNullable(tableService.listDataTypes())
                .map(ResultVO::success)
                .orElseThrow();
    }

    @DeleteMapping("/truncate")
    public ResultVO<Boolean> truncateTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.truncateTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @DeleteMapping("/clear")
    public ResultVO<Boolean> clearTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.clearTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/ddl")
    public ResultVO<Void> getTableDDLSQL(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PostMapping("/similarTable")
    public ResultVO<Void> createSimilarTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PutMapping("/rename")
    public ResultVO<Boolean> renameTable(@RequestBody RenameDTO renameDTO) {
        return Optional.ofNullable(tableService.renameTable(renameDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/data")
    public ResultVO<DataShowVO> showData(Long datasourceId, String databaseName, String tableName, Integer pageSize, Integer pageNum) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setDatasourceId(datasourceId);
        dataQuery.setTableName(tableName);
        dataQuery.setDatabaseName(databaseName);
        dataQuery.setPageNum(pageNum);
        dataQuery.setPageSize(pageSize);
        return Optional.ofNullable(tableService.showData(dataQuery.getDatasourceId(), dataQuery))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
