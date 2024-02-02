package cn.ikangjia.yumi.dms.manager.impl;

import cn.ikangjia.yumi.dms.api.query.DataQuery;
import cn.ikangjia.yumi.dms.core.ExecuteHandler;
import cn.ikangjia.yumi.dms.core.entity.CharacterSetEntity;
import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import cn.ikangjia.yumi.dms.core.entity.Database;
import cn.ikangjia.yumi.dms.core.entity.DatabaseEntity;
import cn.ikangjia.yumi.dms.core.sqlbuilder.CommonSQL;
import cn.ikangjia.yumi.dms.core.sqlbuilder.DatabaseSQL;
import cn.ikangjia.yumi.dms.core.sqlbuilder.table.TableSQL;
import cn.ikangjia.yumi.dms.core.sqlbuilder.ViewSQL;
import cn.ikangjia.yumi.dms.manager.MySQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Service
public class MySQLManagerImpl implements MySQLManager {
    private final ExecuteHandler handler;

    public MySQLManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public void createDatabase(long datasourceId, DatabaseEntity database) {
        String databaseName = database.getDatabaseName();
        String characterSet = database.getCharacterSet();
        String sql;
        if (!StringUtils.hasText(characterSet) || "默认".equalsIgnoreCase(characterSet)) {
            sql = String.format(DatabaseSQL.database_create_1, databaseName);
        } else {
            String collation = database.getCollation();
            if (!StringUtils.hasText(collation) || "默认".equalsIgnoreCase(collation)) {
                sql = String.format(DatabaseSQL.database_create_2, databaseName, characterSet);
            } else {
                sql = String.format(DatabaseSQL.database_create_3, databaseName, characterSet, collation);
            }
        }
        log.info("创建数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void dropDatabase(long datasourceId, String databaseName) {
        String sql = String.format(DatabaseSQL.database_drop, databaseName);
        log.info("删除数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void alterDatabase(long datasourceId, String databaseName, String characterSet, String collation) {
        String sql = String.format(DatabaseSQL.database_alter, databaseName, characterSet, collation);
        log.info("修改数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public DatabaseEntity getDatabaseInfo(long datasourceId, String databaseName) {
        String sql = String.format(DatabaseSQL.database_info, databaseName);
        List<Database> databaseList = handler.executeQuery(Database.class, sql);
        Database database = databaseList.getFirst();

        DatabaseEntity databaseEntity = new DatabaseEntity();
        BeanUtils.copyProperties(database, databaseEntity);

        // 查询 MySQL 版本
        String version = handler.executeQueryString(DatabaseSQL.database_version, "version");
        databaseEntity.setVersion(version);

        // 查询 MySQL DDL
        String ddl = handler.executeQueryString(
                String.format(DatabaseSQL.database_ddl, databaseName),
                "Create Database"
        );
        databaseEntity.setDatabaseDDL(ddl);

        // 表数量
        String tableCount = handler.executeQueryString(
                String.format(DatabaseSQL.database_table_count, databaseName),
                "tableCount"
        );

        databaseEntity.setTableCount(tableCount);
        // 视图数量
        String viewCount = handler.executeQueryString(
                String.format(DatabaseSQL.database_view_count, databaseName),
                "viewCount"
        );
        databaseEntity.setViewCount(viewCount);
        // 存储过程数量

        return databaseEntity;
    }

    @Override
    public List<String> listDatabase(long datasourceId, boolean systemTableShow) {
        return handler.executeQueryStrings("show databases;", "Database");
    }

    @Override
    public List<String> listTable(long datasourceId, String databaseName) {
        return handler.executeQueryStrings(String.format(TableSQL.table_select, databaseName), "TABLE_NAME");
    }

    @Override
    public List<String> listView(long datasourceId, String databaseName) {
        return handler.executeQueryStrings(String.format(ViewSQL.view_select, databaseName), "TABLE_NAME");
    }

    @Override
    public List<CharacterSetEntity> listCharacterSets(Long datasourceId) {
        return handler.executeQuery(CharacterSetEntity.class, CommonSQL.select_character_Set);
    }

    @Override
    public List<String> listCollations(Long datasourceId, String characterSet) {
        String sql = String.format(CommonSQL.select_collation, characterSet);
        return handler.executeQueryStrings(sql, "collation");
    }

    @Override
    public DataEntity showTableData(Long datasourceId, DataQuery dataQuery) {
        String sql = String.format(TableSQL.table_select_data,
                dataQuery.getDatabaseName(),
                dataQuery.getTableName(),
                (dataQuery.getPageNum() - 1) * dataQuery.getPageSize(),
                dataQuery.getPageSize());
        return handler.executeQueryForData(sql);
    }

    @Override
    public Long countTableDataRows(Long datasourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_Row_count, databaseName, tableName);
        return Long.valueOf(handler.executeQueryString(sql, "total"));
    }
}
