package cn.ikangjia.yumi.dms.core.mysql;

import cn.ikangjia.yumi.dms.service.UserService;
import cn.ikangjia.yumi.dms.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 16:24
 */
@Slf4j
public class MySQLConnection {

    public Connection connect(Long datasourceId) {
        // 根据数据源 id 获取数据源信息
        UserService userService = SpringUtil.getBean("userServiceImpl");
        System.out.println(userService.getUser(1L));
        return null;
    }

    public void close(Connection connection) {
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
