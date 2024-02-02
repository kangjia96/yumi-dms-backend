package cn.ikangjia.yumi.dms.util;

import cn.ikangjia.yumi.dms.api.vo.TreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public class TreeUtil {

    // 1-db-3-pomelo，3 指的是数据源 id，pomelo 为数据库名称
    private static final String node_key_level_0_db = "0-db-%s-%s";

    private static final String node_key_level_1_t = "1-t-%s-%s";
    private static final String node_key_level_1_v = "1-v-%s-%s";
    private static final String node_key_level_1_p = "1-p-%s-%s";

    private static final String node_key_level_2_t = "2-t-%s-%s-%s";
    private static final String node_key_level_2_v = "2-v-%s-%s-%s";
    private static final String node_key_level_2_p = "2-p-%s-%s-%s";

    public static List<TreeVO> buildLevel0(Long datasourceId, List<String> itemList) {
        List<TreeVO> result = new ArrayList<>();
        itemList.forEach(item -> {
            TreeVO level0 = new TreeVO();
            level0.setLeaf(false);
            level0.setLabelName(item);
            level0.setNodeKey(String.format(node_key_level_0_db, datasourceId, item));
            result.add(level0);
        });

        return result;
    }

    public static List<TreeVO> buildLevel1(Long datasourceId, String fatherNodeKey) {
        TreeVO table = new TreeVO();
        table.setLeaf(false);
        table.setLabelName("表");
        table.setNodeKey(String.format(node_key_level_1_t, datasourceId, fatherNodeKey));

        TreeVO view = new TreeVO();
        view.setLeaf(false);
        view.setLabelName("视图");
        view.setNodeKey(String.format(node_key_level_1_v, datasourceId, fatherNodeKey));

        TreeVO procedure = new TreeVO();
        procedure.setLeaf(false);
        procedure.setLabelName("存储过程");
        procedure.setNodeKey(String.format(node_key_level_1_p, datasourceId, fatherNodeKey));

        List<TreeVO> result = new ArrayList<>();
        result.add(table);
        result.add(view);
        result.add(procedure);
        return result;
    }

    public static List<TreeVO> buildLevel2(Long datasourceId, String fatherNodeKey, List<String> itemList, Integer type) {
        List<TreeVO> result = new ArrayList<>();
        itemList.forEach(item -> {
            TreeVO level2 = new TreeVO();
            level2.setLeaf(true);
            level2.setLabelName(item);
            switch (type) {
                case 0 -> level2.setNodeKey(String.format(node_key_level_2_t, datasourceId, fatherNodeKey, item));
                case 1 -> level2.setNodeKey(String.format(node_key_level_2_v, datasourceId, fatherNodeKey, item));
                case 2 -> level2.setNodeKey(String.format(node_key_level_2_p, datasourceId, fatherNodeKey, item));
                default -> throw new RuntimeException("树结构数据类型解析错误！");
            }
            result.add(level2);
        });

        return result;
    }
}
