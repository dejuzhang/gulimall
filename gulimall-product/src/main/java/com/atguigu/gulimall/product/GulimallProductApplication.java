package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 整合mybatis-plus
 * 1. 引入maven依赖
 * 2. 配置数据源
 *  2.1 导入mysql的驱动包
 *  2.2 在yaml配置mysql
 * 3.配置mybatis-plus
 *  3.1 使用@MapperScan
 *  3.2 告诉mybatis-plus，sql映射文件的位置（在yaml文件中配置）
 */
@MapperScan("com.atguigu.gulimall.product.dao") //如果dao层的接口没有加@Mapper注解，就需要这个包扫描器
@EnableDiscoveryClient // 开启注册发现
@SpringBootApplication
@EnableFeignClients(basePackages = "com.atguigu.gulimall.product.feign")
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
