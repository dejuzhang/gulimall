package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.BrandDao;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;

/**
 * @author deju
 */
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String)params.get("key");
        if (StringUtils.isEmpty(key)) {
            IPage<BrandEntity> page =
                this.page(new Query<BrandEntity>().getPage(params), new QueryWrapper<BrandEntity>());
            return new PageUtils(page);
        } else {
            QueryWrapper<BrandEntity> wrapper = new QueryWrapper<BrandEntity>();
            wrapper.like("brand_id", key).or().like("name", key);

            IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public void updateCascade(BrandEntity brand) {
        this.updateById(brand);
        // 修改
        if (!brand.getName().isEmpty()){
            // 同步更新其他的表
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            // TODO：更新其他关联
        }
    }

}