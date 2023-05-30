package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author dejuz
 * @Date 2023/5/17 20:30
 */
@FeignClient("gulimall-coupon") // 需要调用的已经在注册中心注册的微服务的名字
public interface CouponFeignService {
    @RequestMapping("coupon/coupon/member/list")
    public R memberCoupon();
}
