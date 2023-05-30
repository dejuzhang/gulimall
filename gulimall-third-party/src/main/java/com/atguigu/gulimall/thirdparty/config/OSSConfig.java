package com.atguigu.gulimall.thirdparty.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author dejuz
 * @Date 2023/5/20 16:38
 */
@Configuration
public class OSSConfig {
    @Value("${spring.cloud.alicloud.access-key}")
    String accessId;
    @Value("${spring.cloud.alicloud.secret-key}")
    String accessKey;
    @Value("${spring.cloud.alicloud.oss.endpoint}")
    String endpoint;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endpoint, accessKey, accessKey);
    }
}
