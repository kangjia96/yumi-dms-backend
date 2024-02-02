package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class SQLResultEntity {
    // 以下三个信息根据 sqlType 的不同（1、2、3）三选一
    public final static String OK = "> OK";
    public final static String affected_rows = "> Affected Rows: %s";
    public final static String error_msg = "> %s - %s";

    public final static String time_consume = "> Time use: %ss";

    // execute(sql) 返回 true，即为有结果集
    public final static int have_resultSet = 1;

    // execute(sql) 返回 false，没有结果集。create、update、insert 等 DDL、DML 语句
    public final static int no_have_resultSet = 2;

    // 执行 SQL 语句出错时，值设置为 3
    public final static int execute_error = 3;

    // 每个 SQL 指定一个唯一 id
    private String sqlId;

    // 待执行的 SQL
    private String sql;

    // SQL 语句类型，有结果集的为 1，没结果集的为 2
    private Integer sqlType;

    // 受影响行数
    private Integer effectRow;

    // 耗时：0.23
    private String timeConsume;

    // 耗时信息：时间：0.23s
    private String timeConsumeInfo;

    // 有结果集的，解析结果集数据于此
    private DataEntity dataEntity;

    // 执行结果信息，成功为 OK，失败为失败详情
    private String tipMsg;

    // 是否执行成功
    private boolean success;
}
