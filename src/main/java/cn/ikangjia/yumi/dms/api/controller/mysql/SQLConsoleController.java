package cn.ikangjia.yumi.dms.api.controller.mysql;

import cn.ikangjia.yumi.dms.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.yumi.dms.core.entity.SQLResultEntity;
import cn.ikangjia.yumi.dms.service.SQLConsoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/sqlConsole")
public class SQLConsoleController {

    private final SQLConsoleService sqlConsoleService;

    public SQLConsoleController(SQLConsoleService sqlConsoleService) {
        this.sqlConsoleService = sqlConsoleService;
    }

    @PostMapping("/execute")
    public List<SQLResultEntity> executeSQL(@RequestBody ExecuteDTO executeDTO) {
        System.out.println("executeDTO = " + executeDTO);
        return sqlConsoleService.executeSQL(executeDTO);
    }

}
