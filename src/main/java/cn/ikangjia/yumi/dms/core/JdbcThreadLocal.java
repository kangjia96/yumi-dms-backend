package cn.ikangjia.yumi.dms.core;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Component
public class JdbcThreadLocal {
    private final ThreadLocal<Connection> context = new ThreadLocal<>();

    public void setContext(Connection conn) {
        if (conn == null) {
            return;
        }
        this.context.set(conn);
    }

    public Connection getContext() {
        return this.context.get();
    }

    public void remove() {
        this.close();
        this.context.remove();
    }

    public void close() {
        Connection conn = context.get();
        try {
            if (conn!=null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
