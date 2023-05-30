package com.atguigu.gulimall.product.service.impl;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryBrandRelationDao categoryBrandRelationDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page =
            this.page(new Query<CategoryEntity>().getPage(params), new QueryWrapper<CategoryEntity>());

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 获取所有的类别
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 1）找到所有的一级分类
        List<CategoryEntity> level1Menu =
            entities.stream().filter(categoryEntity -> categoryEntity.getParentCid() == 0).map((menu) -> {
                menu.setChildren(getChildrenMenu(menu, entities));
                return menu;
            }).sorted((menu1, menu2) -> {
                return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0
                    : menu2.getSort());
            }).collect(Collectors.toList());
        return level1Menu;
    }

    /**
     * 删除菜单
     *
     * @param list
     */
    @Override
    public void removeMenusByIds(List<Long> list) {
        // TODO：1. 需要先判断要删除的菜单有没有被使用
        baseMapper.deleteBatchIds(list);
    }

    /**
     * 找到完整的路径
     *
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        ArrayList<Long> paths = new ArrayList<>();

        List<Long> parentPaths = findParentPaths(catelogId, paths);
        Collections.reverse(parentPaths);
        return parentPaths.toArray(new Long[0]);
    }

    /**
     * 级联更新所有的有关数据
     * @param category
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        if (!category.getName().isEmpty()){
            categoryBrandRelationDao.updateCategroy(category.getCatId(), category.getName());
        }
    }

    private List<Long> findParentPaths(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        // 查询当前分类的信息
        CategoryEntity categoryEntity = this.getById(catelogId);
        // 如果当前分类还有父分类就一直查
        if (categoryEntity.getParentCid() != 0) {
            findParentPaths(categoryEntity.getParentCid(), paths);
        }
        return paths;
    }

    /**
     * 在所有的菜单中，查询当前菜单的子菜单
     *
     * @param currMenu
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildrenMenu(CategoryEntity currMenu, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid().equals(currMenu.getCatId());
        }).map(categoryEntity -> {
            // 递归设置子菜单
            categoryEntity.setChildren(getChildrenMenu(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
    }

}