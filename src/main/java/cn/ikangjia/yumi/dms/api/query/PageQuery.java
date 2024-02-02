package cn.ikangjia.yumi.dms.api.query;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class PageQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
}
