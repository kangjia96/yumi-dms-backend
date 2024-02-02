package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.vo.TreeVO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface ViewService {
    /**
     * 查询指定库下所有的视图
     *
     * @param datasourceId 数据源 id
     * @param databaseName 数据库名称
     * @return 表列表
     */
    List<TreeVO> listTree2View(Long datasourceId, String databaseName);
}
