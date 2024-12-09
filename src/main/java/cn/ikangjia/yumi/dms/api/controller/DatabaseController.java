package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.dto.DatabaseAddDTO;
import cn.ikangjia.yumi.dms.api.dto.DatabaseAlterDTO;
import cn.ikangjia.yumi.dms.api.vo.DatabaseInfoVO;
import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.core.entity.CharacterSetEntity;
import cn.ikangjia.yumi.dms.service.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * 获取数据库列表
     *
     * @param datasourceId 数据源id
     * @return 数据库列表
     */
    @GetMapping("/tree0")
    public List<TreeVO> listDatabases(Long datasourceId) {
        return databaseService.listDatabases(datasourceId);
    }


    /**
     * 获取数据库下的表
     *
     * @param datasourceId 数据源id
     * @param databaseName 数据库名
     * @return 数据库下的表
     */
    @GetMapping("/tree1")
    public List<TreeVO> listTree1(String databaseName, Long datasourceId) {
        return databaseService.listTree1(datasourceId, databaseName);
    }


    /**
     * 获取数据库信息
     *
     * @param datasourceId 数据源id
     * @param databaseName 数据库名
     * @return 数据库信息
     */
    @GetMapping("/{databaseName}")
    public DatabaseInfoVO getDatabaseInfo(Long datasourceId, @PathVariable String databaseName) {
        return databaseService.getDatabaseInfo(datasourceId, databaseName);
    }


    /**
     * 获取字符集
     *
     * @param datasourceId 数据源id
     * @return 字符集
     */
    @GetMapping("/characterSet")
    public List<CharacterSetEntity> getCharacterSets(Long datasourceId) {
        return databaseService.getCharacterSets(datasourceId);
    }

    /**
     * 获取排序规则
     *
     * @param datasourceId 数据源id
     * @param characterSet 字符集
     * @return 排序规则
     */
    @GetMapping("/collation")
    public List<String> getCharacterSets(Long datasourceId, String characterSet) {
        return databaseService.getCollations(datasourceId, characterSet);
    }


    /**
     * 新增数据库
     *
     * @param addDTO 数据库信息
     * @return 是否新增成功
     */
    @PostMapping
    public Boolean addDatabase(@RequestBody DatabaseAddDTO addDTO) {
        return databaseService.addDatabase(addDTO);
    }


    /**
     * 删除数据库
     *
     * @param datasourceId 数据源id
     * @param databaseName 数据库名
     * @return 是否删除成功
     */
    @DeleteMapping("/{databaseName}")
    public Boolean dropDatabase(Long datasourceId, @PathVariable String databaseName) {
        return databaseService.dropDatabase(datasourceId, databaseName);
    }

    /**
     * 修改数据库
     *
     * @param alterDTO 数据库信息
     * @return 是否修改成功
     */
    @PutMapping
    public Boolean alterDatabase(@RequestBody DatabaseAlterDTO alterDTO) {
        return databaseService.alterDatabase(alterDTO);
    }

    /**
     * 获取数据库默认的字符集和排序规则
     *
     * @param datasourceId 数据源id
     * @param databaseName 数据库名
     * @return 数据库默认的字符集和排序规则
     */
    @GetMapping("/defaultCharacter")
    public DatabaseAlterDTO defaultCharacter(Long datasourceId, String databaseName) {
        return databaseService.defaultCharacter(datasourceId, databaseName);
    }
}
