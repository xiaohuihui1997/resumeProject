package com.wjz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.wjz"})
@MapperScan("com.wjz.mapper")//扫描mapper文件夹
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        log.info("项目启动成功...");//根据@Slf4j注释可以使用他在控制台输出东西
    }

}
