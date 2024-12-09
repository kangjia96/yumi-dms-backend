package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.dto.DataOperateDTO;
import cn.ikangjia.yumi.dms.service.DataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping
    public Boolean operateData(@RequestBody DataOperateDTO dataOperateDTO) {
        return dataService.operateData(dataOperateDTO);
    }

}
