package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.query.PageQuery;
import cn.ikangjia.yumi.dms.api.vo.DatasourceVO;
import cn.ikangjia.yumi.dms.api.vo.TestConnectionVO;
import cn.ikangjia.yumi.dms.core.MySQLDatasourceUtil;
import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;
import cn.ikangjia.yumi.dms.domain.mapper.DatasourceMapper;
import cn.ikangjia.yumi.dms.service.DatasourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Service
public class DatasourceServiceImpl implements DatasourceService {
    private final DatasourceMapper datasourceMapper;

    public DatasourceServiceImpl(DatasourceMapper datasourceMapper) {
        this.datasourceMapper = datasourceMapper;
    }

    @Override
    public DatasourceDO getDatasource(Long datasourceId) {
        return datasourceMapper.selectById(datasourceId);
    }

    @Override
    public DatasourceVO listDatasource(PageQuery pageQuery) {
        IPage<DatasourceDO> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        QueryWrapper<DatasourceDO> wrapper = new QueryWrapper<>();

        if (StringUtils.hasText(pageQuery.getKeyword())) {
            wrapper.like("name", pageQuery.getKeyword());
        }
        IPage<DatasourceDO> selectPage = datasourceMapper.selectPage(page, wrapper);

        DatasourceVO result = new DatasourceVO();
        result.setDatasourceDOList(selectPage.getRecords());
        result.setTotal(datasourceMapper.selectCount(null));
        return result;
    }

    @Override
    public Boolean updateDatasource(DatasourceDO datasourceDO) {
        return datasourceMapper.updateById(datasourceDO) == 1;
    }

    @Override
    public Boolean removeDatasource(Long datasourceId) {
        return datasourceMapper.deleteById(datasourceId) == 1;
    }

    @Override
    public Boolean removeDatasourceBatch(List<Long> datasourceIdList) {
        datasourceMapper.deleteBatchIds(datasourceIdList);
        return true;
    }

    @Override
    public Boolean saveDatasource(DatasourceDO datasourceDO) {
        datasourceMapper.insert(datasourceDO);
        return true;
    }

    @Override
    public TestConnectionVO testConnection(DatasourceDO datasourceDO) {
        TestConnectionVO result = new TestConnectionVO();
        switch (datasourceDO.getType()) {
            case "1":
                try {
                    Boolean connected = MySQLDatasourceUtil.testConnection(datasourceDO);
                    result.setConnected(connected);
                    result.setMsg("可成功连接");
                } catch (SQLException e) {
                    result.setConnected(false);
                    result.setMsg(e.getMessage());
                }
                return result;
            case "2":
                throw new RuntimeException("暂不支持的数据库类型");
        };
        return result;
    }
}
