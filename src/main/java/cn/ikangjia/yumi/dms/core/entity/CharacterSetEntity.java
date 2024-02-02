package cn.ikangjia.yumi.dms.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class CharacterSetEntity {
    private String characterSet;
    private String defaultCollation;
    private String description;
    private String maxLen;
}
