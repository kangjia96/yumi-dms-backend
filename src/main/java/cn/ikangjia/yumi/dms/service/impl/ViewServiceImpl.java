package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.util.TreeUtil;
import cn.ikangjia.yumi.dms.manager.MySQLManager;
import cn.ikangjia.yumi.dms.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Service
public class ViewServiceImpl implements ViewService {

    private final MySQLManager mySQLManager;

    public ViewServiceImpl(MySQLManager mySQLManager) {
        this.mySQLManager = mySQLManager;
    }


    @Override
    public List<TreeVO> listTree2View(Long datasourceId, String databaseName) {
        List<String> viewNameList = mySQLManager.listView(datasourceId, databaseName);

        return TreeUtil.buildLevel2(datasourceId, databaseName, viewNameList, 1);
    }
}
