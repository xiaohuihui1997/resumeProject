package com.wjz.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.wjz.utils.MyDateHandler;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;

@CacheConfig
public class MyBatisPlusConfig {
    /**
     * 自动填充功能
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MyDateHandler());
        return globalConfig;
    }
}
