package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.atguigu.gulimall.product.entity.AttrEntity;
import com.atguigu.gulimall.product.service.AttrAttrgroupRelationService;
import com.atguigu.gulimall.product.service.AttrService;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.AttrGroupWithAttrVo;
import com.atguigu.gulimall.product.vo.AttrIdAndAttrGroupIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gulimall.product.entity.AttrGroupEntity;
import com.atguigu.gulimall.product.service.AttrGroupService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;

/**
 * 属性分组
 *
 * @author zhangdeju
 * @email hellodeju@gmail.com
 * @date 2023-05-17 15:43:22
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    AttrGroupService attrGroupService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    @Autowired
    CategoryService categoryService;

    /**
     * 获取分类下的所有的分组以及分组的所有的属性
     *
     * @return
     */
    //    /product/attrgroup/{catelogId}/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttr(@PathVariable Long catelogId) {

        List<AttrGroupWithAttrVo> vos = attrGroupService.getAttrGroupWithAttrByCatelogId(catelogId);

        return R.ok().put("data", vos);
    }

    /**
     * 新增属性分组关联关系
     *
     * @return
     */
    @PostMapping("/attr/relation")
    public R addRelationAttr(@RequestBody List<AttrIdAndAttrGroupIdVo> vos) {
        relationService.saveAttrRelation(vos);
        return R.ok();
    }

    /**
     * 根据属性组id获取属性值 //    /product/attrgroup/{attrgroupId}/attr/relation
     *
     * @param attrgroupId
     * @return
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entities = attrGroupService.getAttrRelation(attrgroupId);
        return R.ok().put("data", entities);
    }

    /**
     * 删除关联
     *
     * @param attrIdAndAttrGroupIdVo
     * @return
     */
    @PostMapping("/attr/relation/delete")
    public R relationDelete(@RequestBody AttrIdAndAttrGroupIdVo[] attrIdAndAttrGroupIdVo) {
        attrGroupService.deleteBatchRelation(attrIdAndAttrGroupIdVo);
        return R.ok();
    }

    ///product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R getNoRelationAttr(@RequestParam Map<String, Object> params,
        @PathVariable("attrgroupId") Long attrgroupId) {
        PageUtils page = attrService.getNoRelationAttr(params, attrgroupId);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        //        PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();

        Long[] path = categoryService.findCatelogPath(catelogId);

        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
