package cn.ikangjia.yumi.dms.service;

import cn.ikangjia.yumi.dms.api.dto.DatabaseAddDTO;
import cn.ikangjia.yumi.dms.api.dto.DatabaseAlterDTO;
import cn.ikangjia.yumi.dms.api.vo.DatabaseInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.core.entity.CharacterSetEntity;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
public interface DatabaseService {
    List<TreeVO> listDatabases(Long datasourceId);

    DatabaseInfoVO getDatabaseInfo(Long datasourceId, String databaseName);

    Boolean addDatabase(DatabaseAddDTO addDTO);

    Boolean dropDatabase(Long datasourceId, String databaseName);

    Boolean alterDatabase(DatabaseAlterDTO alterDTO);

    List<TreeVO> listTree1(Long datasourceId, String databaseName);

    List<CharacterSetEntity> getCharacterSets(Long datasourceId);

    List<String> getCollations(Long datasourceId, String characterSet);

    DatabaseAlterDTO defaultCharacter(Long datasourceId, String databaseName);
}
