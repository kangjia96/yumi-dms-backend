package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.yumi.dms.core.entity.SQLResultEntity;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
public interface SQLConsoleService {
    List<SQLResultEntity> executeSQL(ExecuteDTO executeDTO);
}
