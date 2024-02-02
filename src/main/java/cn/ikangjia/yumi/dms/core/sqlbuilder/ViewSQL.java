package cn.ikangjia.yumi.dms.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface ViewSQL {
    String view_select = """
            select table_name from information_schema.tables
            where table_schema = '%s' and table_type in('VIEW', 'SYSTEM VIEW');
            """;
}
