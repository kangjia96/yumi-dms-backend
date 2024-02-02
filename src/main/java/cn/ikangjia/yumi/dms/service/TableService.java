package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.dto.table.RenameDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableDTO;
import cn.ikangjia.yumi.dms.api.dto.table.TableCreateDTO;
import cn.ikangjia.yumi.dms.api.query.DataQuery;
import cn.ikangjia.yumi.dms.api.vo.TableInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.api.vo.data.DataShowVO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface TableService {
    /**
     * 查询指定库下所有的表
     *
     * @param datasourceId 数据源 id
     * @param databaseName 数据库名称
     * @return 表列表
     */
    List<TreeVO> listTree2Table(Long datasourceId, String databaseName);

    DataShowVO showData(Long datasourceId, DataQuery dataQuery);

    String createTable(TableCreateDTO tableCreateDTO);

    List<String> listDataTypes();

    Boolean clearTable(TableDTO tableDTO);

    Boolean truncateTable(TableDTO tableDTO);

    Boolean dropTable(TableDTO tableDTO);

    Boolean renameTable(RenameDTO renameDTO);

    TableInfoVO getTableInfo(TableDTO tableDTO);
}
