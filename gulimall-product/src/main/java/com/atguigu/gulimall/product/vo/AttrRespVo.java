package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.AttrEntity;
import lombok.Data;

/**
 * @Author dejuz
 * @Date 2023/5/22 19:34
 */
@Data
public class AttrRespVo extends AttrVo {
    private String catelogName;

    private String groupName;

    // 所属路径
    private Long[] catelogPath;
}
