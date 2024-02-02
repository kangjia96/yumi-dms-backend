package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.query.PageQuery;
import cn.ikangjia.yumi.dms.api.vo.DatasourceVO;
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

    @GetMapping("/{datasourceId}")
    public DatasourceDO getDatasource(@PathVariable Long datasourceId) {
        return datasourceService.getDatasource(datasourceId);
    }

    @GetMapping
    public DatasourceVO listDatasource(Integer pageSize, Integer pageNum, String keyword){
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNum(pageNum);
        pageQuery.setKeyword(keyword);
        return datasourceService.listDatasource(pageQuery);
    }

    @PutMapping
    public Boolean updateDatasource(@RequestBody DatasourceDO datasourceDO){
        return datasourceService.updateDatasource(datasourceDO);
    }

    @DeleteMapping("/{datasourceId}")
    public Boolean removeDatasource(@PathVariable Long datasourceId){
        return datasourceService.removeDatasource(datasourceId);
    }

    @DeleteMapping("/batchDelete")
    public Boolean removeDatasourceBatch(List<Long> datasourceIdList){
        return datasourceService.removeDatasourceBatch(datasourceIdList);
    }

    @PostMapping
    public Boolean saveDatasource(@RequestBody DatasourceDO datasourceDO){
        return datasourceService.saveDatasource(datasourceDO);
    }

    /**
     * 测试数据源的连通性
     *
     * @param datasourceDO 数据源信息
     * @return 测试结果
     */
    @PostMapping("/testConnection")
    public TestConnectionVO testConnection(@RequestBody DatasourceDO datasourceDO){
        return datasourceService.testConnection(datasourceDO);
    }
}
