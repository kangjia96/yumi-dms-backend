package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.vo.TestConnectionVO;
import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface DatasourceService {

    DatasourceDO getDatasource(Long datasourceId);

    List<DatasourceDO> listDatasource(String keyword);

    Boolean updateDatasource(DatasourceDO datasourceDO);

    Boolean removeDatasource(Long datasourceId);

    Boolean saveDatasource(DatasourceDO datasourceDO);

    /**
     * 测试 MySQL 数据源的连通性
     *
     * @param datasourceDO 数据源信息
     * @return 测试结果
     */
    TestConnectionVO testConnection(DatasourceDO datasourceDO);

}
