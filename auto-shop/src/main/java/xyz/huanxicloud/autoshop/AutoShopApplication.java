package xyz.huanxicloud.autoshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.huanxicloud.autoshop.dao.mapper.")
public class AutoShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoShopApplication.class, args);
    }
}
