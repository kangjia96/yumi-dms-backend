package cn.ikangjia.yumi.dms.service.impl;

import cn.ikangjia.yumi.dms.api.dto.DatabaseAddDTO;
import cn.ikangjia.yumi.dms.api.dto.DatabaseAlterDTO;
import cn.ikangjia.yumi.dms.api.vo.DatabaseInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.util.TreeUtil;
import cn.ikangjia.yumi.dms.core.entity.CharacterSetEntity;
import cn.ikangjia.yumi.dms.core.entity.DatabaseEntity;
import cn.ikangjia.yumi.dms.domain.entity.DatasourceDO;
import cn.ikangjia.yumi.dms.domain.mapper.DatasourceMapper;
import cn.ikangjia.yumi.dms.manager.MySQLManager;
import cn.ikangjia.yumi.dms.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {
    private final MySQLManager mySQLManager;
    private final DatasourceMapper datasourceMapper;

    public DatabaseServiceImpl(MySQLManager mySQLManager, DatasourceMapper datasourceMapper) {
        this.mySQLManager = mySQLManager;
        this.datasourceMapper = datasourceMapper;
    }

    @Override
    public List<TreeVO> listDatabases(Long datasourceId) {
        List<String> dbNameList = mySQLManager.listDatabase(datasourceId, true);
        return TreeUtil.buildLevel0(datasourceId, dbNameList);
    }

    @Override
    public DatabaseInfoVO getDatabaseInfo(Long datasourceId, String databaseName) {
        // 获取数据源信息
        DatasourceDO datasourceDO = datasourceMapper.selectById(datasourceId);
        datasourceDO.setPassword("");

        // 获取 mysql 相关信息
        DatabaseInfoVO result = new DatabaseInfoVO();
        result.setDatasourceDO(datasourceDO);

        DatabaseEntity databaseInfo = mySQLManager.getDatabaseInfo(datasourceId, databaseName);
        BeanUtils.copyProperties(databaseInfo, result);
        System.out.println(result);
        return result;
    }

    @Override
    public Boolean addDatabase(DatabaseAddDTO addDTO) {
        DatabaseEntity database = new DatabaseEntity();
        database.setDatabaseName(addDTO.getDatabaseName());
        database.setCharacterSet(addDTO.getCharacterSet());
        database.setCollation(addDTO.getCollation());
        mySQLManager.createDatabase(addDTO.getDatasourceId(), database);
        return true;
    }

    @Override
    public Boolean dropDatabase(Long datasourceId, String databaseName) {
        mySQLManager.dropDatabase(datasourceId, databaseName);
        return true;
    }

    @Override
    public Boolean alterDatabase(DatabaseAlterDTO alterDTO) {
        mySQLManager.alterDatabase(alterDTO.getDatasourceId(), alterDTO.getDatabaseName(), alterDTO.getCharacterSet(), alterDTO.getCollation());
        return true;
    }

    @Override
    public List<TreeVO> listTree1(Long datasourceId, String databaseName) {
        return TreeUtil.buildLevel1(datasourceId, databaseName);
    }

    @Override
    public List<CharacterSetEntity> getCharacterSets(Long datasourceId) {
        CharacterSetEntity defaultCharacterSet = new CharacterSetEntity();
        defaultCharacterSet.setCharacterSet("默认");
        defaultCharacterSet.setDefaultCollation("默认");
        defaultCharacterSet.setDefaultCollation("这是默认的字符集");
        List<CharacterSetEntity> characterSetEntityList = mySQLManager.listCharacterSets(datasourceId);
        characterSetEntityList.addFirst(defaultCharacterSet);
        return characterSetEntityList;
    }

    @Override
    public List<String> getCollations(Long datasourceId, String characterSet) {
        List<String> collationList = mySQLManager.listCollations(datasourceId, characterSet);
        collationList.addFirst("默认");
        return collationList;
    }

    @Override
    public DatabaseAlterDTO defaultCharacter(Long datasourceId, String databaseName) {
        DatabaseEntity databaseInfo = mySQLManager.getDatabaseInfo(datasourceId, databaseName);
        DatabaseAlterDTO result = new DatabaseAlterDTO();
        result.setDatabaseName(databaseName);
        result.setCharacterSet(databaseInfo.getCharacterSet());
        result.setCollation(databaseInfo.getCollation());

        return result;
    }
}
