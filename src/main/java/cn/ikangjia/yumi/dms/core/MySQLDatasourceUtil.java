package cn.ikangjia.yumi.dms.core;

import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public class MySQLDatasourceUtil {
    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s";

    /**
     * 获取 MySQL 类型的数据源连接
     *
     * @param datasourceDO 数据源参数
     * @return 数据库连接
     * @throws SQLException 创建失败的异常
     */
    public static Connection getConnection(DatasourceDO datasourceDO) throws SQLException {
        String username = datasourceDO.getUsername();
        String password = datasourceDO.getPassword();
        String host = datasourceDO.getHost();
        String port = datasourceDO.getPort();

        String url = String.format(URL_TEMPLATE, host, port);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 测试 MySQL 数据源信息是否正确
     *
     * @param datasourceDO 数据源参数
     * @return 数据库连接
     * @throws SQLException 创建失败的异常
     */
    public static Boolean testConnection(DatasourceDO datasourceDO) throws SQLException {
        return testConnection(datasourceDO.getHost(), datasourceDO.getPort(), datasourceDO.getUsername(), datasourceDO.getPassword());
    }

    private static Boolean testConnection(String host, String port, String username, String password) throws SQLException {
        String url = String.format(URL_TEMPLATE, host, port);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return statement.execute("select 1");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
