package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.yumi.dms.core.entity.SQLResultEntity;
import cn.ikangjia.yumi.dms.manager.SQLManager;
import cn.ikangjia.yumi.dms.service.SQLConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Service
public class SQLConsoleServiceImpl implements SQLConsoleService {

    private final SQLManager sqlManager;

    public SQLConsoleServiceImpl(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    @Override
    public List<SQLResultEntity> executeSQL(ExecuteDTO executeDTO) {
        Long datasourceId = executeDTO.getDatasourceId();
        String databaseName = executeDTO.getDatabaseName();
        String sqlStr = executeDTO.getSqlStr();

        // 拆分并校验 SQL 语句
        List<String> sqlList = handleSQLStr(sqlStr);
        List<SQLResultEntity> sqlResultEntityList = sqlManager.executeSQLBatch(datasourceId, databaseName, sqlList);

        // 结果进一步处理
        System.out.println(sqlResultEntityList);
        return sqlResultEntityList;
    }

    private List<String> handleSQLStr(String sqlStr) {
        return Arrays.stream(sqlStr.split(";")).toList();
    }
}
