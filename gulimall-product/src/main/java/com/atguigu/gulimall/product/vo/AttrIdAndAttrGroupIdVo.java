package com.atguigu.gulimall.product.vo;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author dejuz
 * @Date 2023/5/23 20:47
 */

@Data
public class AttrIdAndAttrGroupIdVo {
//    [{"attrId":1,"attrGroupId":2}]
    private Long attrId;
    private Long attrGroupId;
}
