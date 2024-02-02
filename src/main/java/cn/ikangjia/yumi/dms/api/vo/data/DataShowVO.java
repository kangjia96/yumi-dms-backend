package cn.ikangjia.yumi.dms.api.vo.data;

import cn.ikangjia.yumi.dms.core.entity.DataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class DataShowVO {

    // 数据
    private DataEntity dataEntity;

    // 表数据总行数
    private Long total;
}
