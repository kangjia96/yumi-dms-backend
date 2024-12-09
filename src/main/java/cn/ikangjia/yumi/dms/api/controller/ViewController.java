package cn.ikangjia.yumi.dms.api.controller;

import cn.ikangjia.yumi.dms.api.vo.TreeVO;
import cn.ikangjia.yumi.dms.service.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@RestController
@RequestMapping("/api/v1/view")
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/tree")
    public List<TreeVO> listTree2View(Long datasourceId, String databaseName) {
        return viewService.listTree2View(datasourceId, databaseName);
    }
}
