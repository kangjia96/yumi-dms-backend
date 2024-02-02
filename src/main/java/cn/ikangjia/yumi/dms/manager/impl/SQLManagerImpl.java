package cn.ikangjia.yumi.dms.manager.impl;

import cn.ikangjia.yumi.dms.core.DMSException;
import cn.ikangjia.yumi.dms.core.ExecuteHandler;
import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import cn.ikangjia.yumi.dms.core.entity.SQLResultEntity;
import cn.ikangjia.yumi.dms.manager.SQLManager;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Service
public class SQLManagerImpl implements SQLManager {

    private final ExecuteHandler handler;

    public SQLManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public List<SQLResultEntity> executeSQLBatch(Long datasourceId, String databaseName, List<String> sqlList) {
        Connection connection = handler.getConnection();
        try {
            if (connection == null || connection.isClosed()) {
                throw new DMSException("获取数据源[" + datasourceId + "]失败：");
            }
        } catch (SQLException e) {
            throw new DMSException("获取数据源[" + datasourceId + "]失败：" + e.getMessage());
        }

        List<SQLResultEntity> result = new ArrayList<>();

        sqlList.forEach(sql -> result.add(executeSQLSingle(connection, sql)));

        return result;
    }

    private SQLResultEntity executeSQLSingle(Connection connection, String sql) {
        SQLResultEntity sqlResult = new SQLResultEntity();

        sqlResult.setSql(sql);

        try (Statement statement = connection.createStatement()) {  // SQL  正常执行
            long startTime = System.currentTimeMillis();  // 开始时间

            // 执行 SQL，执行结果为 true 则有结果集，false 则无结果集
            boolean hasResultSet = statement.execute(sql);

            long endTime = System.currentTimeMillis();  // 结束时间

            // 计算耗时，单位转换成秒
            long time = endTime - startTime;
            String timeConsume = String.valueOf(((double) time) / 1000);
            String timeConsumeInfo = String.format(SQLResultEntity.time_consume, timeConsume);
            sqlResult.setTimeConsume(timeConsume);
            sqlResult.setTimeConsumeInfo(timeConsumeInfo);

            if (hasResultSet) { // 该分支处理带结果集的 SQL 执行
                sqlResult.setSqlType(SQLResultEntity.have_resultSet);  // sql 类型视之为 1，即有结果集

                // 解析结果集
                ResultSet resultSet = statement.getResultSet();
                ResultSetMetaData metaData = resultSet.getMetaData();

                // 接收解析结果
                DataEntity dataEntity = new DataEntity();

                // 解析列名
                int columnCount = metaData.getColumnCount();  // 获取列数
                List<String> columnNameList = new ArrayList<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    columnNameList.add(metaData.getColumnLabel(i));
                }
                dataEntity.setColumnNameList(columnNameList);

                // 解析数据
                List<Map<String, Object>> dataMapList = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> rowMap = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnLabel = metaData.getColumnLabel(i);
                        Object value = resultSet.getObject(columnLabel);
                        rowMap.put(columnLabel, value);
                    }
                    dataMapList.add(rowMap);
                }
                dataEntity.setDataMapList(dataMapList);

                sqlResult.setDataEntity(dataEntity);
            } else {  // 该分支处理不带结果集的 SQL 执行
                sqlResult.setSqlType(SQLResultEntity.no_have_resultSet);
                int updateCount = statement.getUpdateCount();
                if (updateCount <= 0) {
                    sqlResult.setEffectRow(0);
                    sqlResult.setTipMsg(SQLResultEntity.OK);
                } else {
                    sqlResult.setEffectRow(updateCount);
                    sqlResult.setTipMsg(String.format(SQLResultEntity.affected_rows, updateCount));
                }
            }
            sqlResult.setSuccess(true);
        } catch (SQLException e) {  // 该语句块用于封装 SQL 执行出错时的错误信息
            sqlResult.setSuccess(false);
            sqlResult.setSqlType(SQLResultEntity.execute_error);
            sqlResult.setTimeConsumeInfo(String.format(SQLResultEntity.time_consume, 0));

            // "tipMsg": "> 1049 - Unknown database 'db_xxx'",
            sqlResult.setTipMsg(String.format(SQLResultEntity.error_msg, e.getErrorCode(), e.getLocalizedMessage()));
        }
        return sqlResult;
    }
}
