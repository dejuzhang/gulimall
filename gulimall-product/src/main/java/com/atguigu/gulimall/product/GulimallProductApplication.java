package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@MapperScan("com.atguigu.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
