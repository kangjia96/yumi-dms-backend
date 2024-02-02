package cn.ikangjia.yumi.dms.core;

import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SQL 执行器
 *
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class ExecuteHandler {
    private final JdbcThreadLocal jdbcThreadLocal;

    public ExecuteHandler(JdbcThreadLocal jdbcThreadLocal) {
        this.jdbcThreadLocal = jdbcThreadLocal;
    }

    public Connection getConnection() {
        return jdbcThreadLocal.getContext();
    }

    /**
     * 可用于执行任何 SQL 语句，返回一个 boolean 值，表明执行该 SQL 语句是否返回了 ResultSet
     *
     * @param sql sql 语句
     * @return 执行结果有 ResultSet 则返回 true，没有则返回 false
     */
    public boolean execute(String sql) {
        try (Statement statement = this.getStatement()) {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    /**
     * 执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句
     *
     * @param sql 要执行的 sql 语句
     * @return 执行结果。INSERT、UPDATE 或 DELETE 返回受影响行数，DDL 返回 0
     */
    public Integer executeUpdate(String sql) {
        try (Statement statement = this.getStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    /**
     * 查询表数据，并将其封装成对象列表
     *
     * @param t      泛型对象
     * @param sql    sql 语句
     * @param params 参数
     * @param <T>    泛型对象
     * @return 对象列表
     */
    public <T> List<T> executeQuery(Class<T> t, String sql, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            return ResultHandler.doObjectResult(t, rs);
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询某表的数据
     *
     * @param sql    sql
     * @param params 参数
     * @return 数据
     */
    public DataEntity executeQueryForData(String sql, String... params) {
        DataEntity result = new DataEntity();
        List<String> columnNameList = new ArrayList<>();
        List<Map<String, Object>> dataListMap;

        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNameList.add(metaData.getColumnLabel(i));
            }
            dataListMap = ResultHandler.doMapResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.setDataMapList(dataListMap);
        result.setColumnNameList(columnNameList);
        return result;
    }

    /**
     * 较为通用的一个查询方法
     *
     * @param sql    sql 语句
     * @param params 参数
     * @return 查询结果
     */
    public List<Map<String, Object>> executeQuery(String sql, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            return ResultHandler.doMapResult(rs);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    /**
     * 返回查询结果集中的指定列所有数据
     *
     * @param sql                   sql 语句
     * @param needReturnColumnLabel 需要返回列数据的列名称
     * @param params                参数
     * @return 列数据
     */
    public List<String> executeQueryStrings(String sql, String needReturnColumnLabel, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();

            List<Map<String, Object>> mapList = ResultHandler.doMapResult(rs);
            if (mapList.size() == 0) {
                return new ArrayList<>();
            }
            return mapList.stream()
                    .map(map -> map.get(needReturnColumnLabel))
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    /**
     * 返回查询结果集中的指定列的数据
     *
     * @param sql                   sql 语句
     * @param needReturnColumnLabel 需要返回列数据的列名称
     * @param params                参数
     * @return 列数据
     */
    public String executeQueryString(String sql, String needReturnColumnLabel, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();

            List<Map<String, Object>> mapList = ResultHandler.doMapResult(rs);
            if (mapList.size() == 0) {
                return "";
            }
            return mapList.stream()
                    .map(map -> map.get(needReturnColumnLabel))
                    .map(String::valueOf)
                    .collect(Collectors.toList())
                    .get(0);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    private Statement getStatement() {
        Connection context = jdbcThreadLocal.getContext();
        try {
            return context.createStatement();
        } catch (SQLException e) {
            log.error("获取 Statement 失败{}", e.getMessage());
            throw new DMSException(e.getMessage(), e);
        }
    }

    private PreparedStatement getPrepareStatement(String sqlTemplate, String... params) {
        Connection context = jdbcThreadLocal.getContext();
        try {
            PreparedStatement statement = context.prepareStatement(sqlTemplate);
            if (params == null || params.length == 0) {
                return statement;
            }
            for (int i = 0, j = params.length; i < j; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement;
        } catch (SQLException e) {
            log.error("获取 PrepareStatement 失败{}", e.getMessage());
            throw new DMSException(e.getMessage(), e);
        }
    }

}
