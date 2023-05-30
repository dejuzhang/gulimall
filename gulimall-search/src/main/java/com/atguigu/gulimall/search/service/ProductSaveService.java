package com.atguigu.gulimall.search.service;

import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

/**
 * @Author dejuz
 * @Date 2023/5/28 18:07
 */
public interface ProductSaveService {
    boolean productStatusUp(@RequestBody List<SkuEsModel> upProducts) throws IOException;
}
