package cn.ikangjia.yumi.dms.api.vo;


import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;
import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DatasourceVO {
    private List<DatasourceDO> datasourceDOList;
    private Long total;
}
