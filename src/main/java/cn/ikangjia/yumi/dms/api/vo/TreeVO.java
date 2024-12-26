package cn.ikangjia.yumi.dms.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Data
public class TreeVO {
    private String pKey; // 父节点 key

    private String key;
    private String label;

    private String type;  // 类型，Database, Folder, Table...

    private Integer level;
    private boolean isLeaf;

    private List<TreeVO> children;
}
