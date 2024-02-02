package cn.ikangjia.yumi.dms.core.sqlbuilder.table;

import lombok.Getter;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface TableSQL {
    String table_select = """
            select table_name from information_schema.tables
            where table_schema = '%s' and table_type = 'BASE TABLE';
            """;

    String table_select_info = """
            select table_name as tableName, table_schema as databaseName, table_type as tableType,
             engine, data_length as dataLength, create_time as createTime, update_time as updateTime,
             table_collation as collation, table_comment as comment from information_schema.tables
            where table_schema = '%s' and table_type = 'BASE TABLE' and table_name = '%s';
            """;

    String table_show_create = "show create table %s.%s;";

    String table_select_data = """
            select * from %s.%s where 1 = 1 limit %s, %s;
            """;

    String table_Row_count = """
            select count(*) as total from %s.%s where 1 = 1;
            """;

    String table_clear = "delete * from %s.%s";

    String table_truncate = "truncate %s.%s;";

    String table_drop = "drop table %s.%s;";

    // RENAME TABLE old_table_name TO new_table_name;
    String table_rename = "RENAME TABLE %s TO %s;";

    /*
     * https://www.yiibai.com/mysql/create-table.html
     * CREATE TABLE [IF NOT EXISTS] table_name(
     *         column_list
     * ) engine=table_type;
     *
     * 列区域：
     * column_name data_type[size] [NOT NULL|NULL] [DEFAULT value]
     * [AUTO_INCREMENT]
     *
     * PRIMARY KEY (col1,col2,...)
     *
     * 示例：
     ** CREATE TABLE IF NOT EXISTS tasks (
      *    task_id INT(11) NOT NULL AUTO_INCREMENT,
      *    subject VARCHAR(45) DEFAULT NULL,
      *    start_date DATE DEFAULT NULL,
      *    end_date DATE DEFAULT NULL,
      *    description VARCHAR(200) DEFAULT NULL,
      *    PRIMARY KEY (task_id)
      *  ) ENGINE=InnoDB;
      *
     */

    String table_create = """
            create table %s (
            
            ) engine=%s;
            """;
    String table_column = """
            %s %s %s
            """;

    @Getter
    enum DataTypeEnum{
        type_bit("bit", "无符号[0,255]，有符号[-128,127]，天缘博客备注：BIT和BOOL布尔型都占用1字节"),

        // MySQL提供BOOLEAN或BOOL作为TINYINT(1)的同义词。
        type_bool("bool", "description"),
        type_boolean("boolean", "description"),

        type_tinyint("tinyint", "1个字节  范围(-128~127)"),
        type_smallint("smallint", "2个字节  范围(-32768~32767)"),
        type_mediumint("mediumint", "3个字节  范围(-8388608~8388607)"),
        type_int("int", "4个字节  范围(-2147483648~2147483647)"),
        type_bigint("bigint", "8个字节  范围(+-9.22*10的18次方)"),

        // 浮点型在数据库中存放的是近似值，而定点类型在数据库中存放的是精确值。
        type_float("float", "float(m,d)单精度浮点型    8位精度(4字节)     m总个数，d小数位"),
        type_double("double", "double(m,d)双精度浮点型    16位精度(8字节)    m总个数，d小数位"),
        type_decimal("decimal", "decimal(m,d) 参数m<65 是总个数，d<30且 d<m 是小数位。"),

        type_DATE("DATE", "日期 '2008-12-2'"),
        type_TIME("TIME", "时间 '12:25:36'"),
        type_YEAR("YEAR", "日期时间 '2008-12-2 22:06:44'"),
        type_DATETIME("DATETIME", "description"),
        type_TIMESTAMP("TIMESTAMP", "自动存储记录修改时间"),

        type_CHAR("CHAR()", "固定长度，最多255个字符"),
        type_VARCHAR("VARCHAR()", "固定长度，最多65535个字符"),
        type_TINYBLOB("TINYBLOB", "description"),
        type_TINYTEXT("TINYTEXT", "可变长度，最多255个字符"),
        type_BLOB("BLOB", "description"),
        type_TEXT("TEXT", "可变长度，最多65535个字符"),
        type_MEDIUMBLOB("MEDIUMBLOB", "description"),
        type_MEDIUMTEXT("MEDIUMTEXT", "可变长度，最多2的24次方-1个字符"),
        type_LONGBLOB("LONGBLOB", "description"),
        type_LONGTEXT("LONGTEXT", "可变长度，最多2的32次方-1个字符"),

        ;

        private final String dataType;
        private final String description;

        DataTypeEnum(String dataType, String description) {
            this.dataType = dataType;
            this.description = description;
        }

        public static List<DataTypeEnum> getAllDataTypes() {
            return List.of(DataTypeEnum.values());
        }
    }
}
