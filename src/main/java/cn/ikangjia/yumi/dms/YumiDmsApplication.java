package cn.ikangjia.yumi.dms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ikangjia.yumi.dms.domain.mapper")
public class YumiDmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YumiDmsApplication.class, args);
    }

}
