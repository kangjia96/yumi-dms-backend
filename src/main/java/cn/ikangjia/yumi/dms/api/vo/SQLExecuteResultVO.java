package cn.ikangjia.yumi.dms.api.vo;

import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import lombok.Data;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class SQLExecuteResultVO {
    private List<DataEntity> dataEntityList;
    private boolean hasResult;
}
