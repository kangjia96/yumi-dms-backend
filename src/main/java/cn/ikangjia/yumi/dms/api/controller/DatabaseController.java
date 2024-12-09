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

    @GetMapping("/tree0")
    public List<TreeVO> listDatabases(Long datasourceId) {
        return databaseService.listDatabases(datasourceId);
    }

    @GetMapping("/tree1")
    public List<TreeVO> listTree1(String databaseName, Long datasourceId) {
        return databaseService.listTree1(datasourceId, databaseName);
    }

    @GetMapping("/{databaseName}")
    public DatabaseInfoVO getDatabaseInfo(Long datasourceId, @PathVariable String databaseName) {
        return databaseService.getDatabaseInfo(datasourceId, databaseName);
    }

    @GetMapping("/characterSet")
    public List<CharacterSetEntity> getCharacterSets(Long datasourceId) {
        return databaseService.getCharacterSets(datasourceId);
    }

    @GetMapping("/collation")
    public List<String> getCharacterSets(Long datasourceId, String characterSet) {
        return databaseService.getCollations(datasourceId, characterSet);
    }

    @PostMapping
    public Boolean addDatabase(@RequestBody DatabaseAddDTO addDTO) {
        return databaseService.addDatabase(addDTO);
    }

    @DeleteMapping("/{databaseName}")
    public Boolean dropDatabase(Long datasourceId, @PathVariable String databaseName) {
        return databaseService.dropDatabase(datasourceId, databaseName);
    }

    @PutMapping
    public Boolean alterDatabase(@RequestBody DatabaseAlterDTO alterDTO) {
        return databaseService.alterDatabase(alterDTO);
    }

    @GetMapping("/defaultCharacter")
    public DatabaseAlterDTO defaultCharacter(Long datasourceId, String databaseName) {
        return databaseService.defaultCharacter(datasourceId, databaseName);
    }
}
