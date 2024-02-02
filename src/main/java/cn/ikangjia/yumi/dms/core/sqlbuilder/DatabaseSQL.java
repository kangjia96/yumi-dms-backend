package cn.ikangjia.yumi.dms.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface DatabaseSQL {
    String database_use = "use %s;";

    String database_show = "show databases;";
    String database_version = "select version() as version;";
    String database_ddl = "show create database %s;";

    String database_drop = "drop database %s;";

    String database_alter = "alter schema '%s' default character set '%s' default collate '%s';";

    String database_create_1 = "create database %s;";
    String database_create_2 = "create database %s default character set %s;";
    String database_create_3 = "create database %s default character set %s default collate %s;";


    String database_info = """
           select schema_name as databaseName, default_character_set_name as characterSet,
           default_collation_name as collation, sql_path as sqlPath
           from information_schema.schemata
           where schema_name = '%s';
            """;
    String database_table_count = """
            select count(*) as tableCount from information_schema.tables
            where 'table_type' = 'base table' and 'table_schema' = '%s';
            """;
    String database_view_count = """
            select count(*) as viewCount from information_schema.tables
            where 'table_type' in ('view', 'system view') and 'table_schema' = '%s';
            """;
}
