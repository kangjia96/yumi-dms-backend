package cn.ikangjia.yumi.dms.domain.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/12/17 15:17
 */
@Data
public class SpaceDO {
    private Long id;
    private String name;
    private String description;
    private String owner;
}
