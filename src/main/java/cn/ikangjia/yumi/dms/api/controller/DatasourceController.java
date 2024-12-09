package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.vo.TestConnectionVO;
import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;
import cn.ikangjia.yumi.dms.service.DatasourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/api/v1/datasource")
public class DatasourceController {

    private final DatasourceService datasourceService;

    public DatasourceController(DatasourceService datasourceService) {
        this.datasourceService = datasourceService;
    }

    /**
     * 获取数据源信息
     *
     * @param datasourceId 数据源ID
     * @return 数据源信息
     */
    @GetMapping("/{datasourceId}")
    public DatasourceDO getDatasource(@PathVariable Long datasourceId) {
        return datasourceService.getDatasource(datasourceId);
    }

    /**
     * 获取数据源列表
     *
     * @param keyword 关键字
     * @return 数据源列表
     */
    @GetMapping
    public List<DatasourceDO> listDatasource(String keyword) {
        return datasourceService.listDatasource(keyword);
    }

    /**
     * 更新数据源
     *
     * @param datasourceDO 数据源信息
     * @return 更新结果
     */
    @PutMapping
    public Boolean updateDatasource(@RequestBody DatasourceDO datasourceDO) {
        return datasourceService.updateDatasource(datasourceDO);
    }

    /**
     * 删除数据源
     *
     * @param datasourceId 数据源ID
     * @return 删除结果
     */
    @DeleteMapping("/{datasourceId}")
    public Boolean removeDatasource(@PathVariable Long datasourceId) {
        return datasourceService.removeDatasource(datasourceId);
    }


    /**
     * 保存数据源
     *
     * @param datasourceDO 数据源信息
     * @return 保存结果
     */
    @PostMapping
    public Boolean saveDatasource(@RequestBody DatasourceDO datasourceDO) {
        return datasourceService.saveDatasource(datasourceDO);
    }

    /**
     * 测试数据源的连通性
     *
     * @param datasourceDO 数据源信息
     * @return 测试结果
     */
    @PostMapping("/testConnection")
    public TestConnectionVO testConnection(@RequestBody DatasourceDO datasourceDO) {
        return datasourceService.testConnection(datasourceDO);
    }
}
