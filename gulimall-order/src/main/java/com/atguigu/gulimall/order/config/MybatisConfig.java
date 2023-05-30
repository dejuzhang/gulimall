package com.atguigu.gulimall.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author dejuz
 * @Date 2023/5/25 20:33
 */
@MapperScan("com.atguigu.gulimall.order.dao")
@Configuration
public class MybatisConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 配置一些参数
        return paginationInterceptor;
    }
}
