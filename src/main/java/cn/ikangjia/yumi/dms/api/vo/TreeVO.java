package cn.ikangjia.yumi.dms.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class TreeVO {
    private String nodeKey;
    private String labelName;
    private Integer level;
    private boolean leaf;
    private List<TreeVO> children;
}
