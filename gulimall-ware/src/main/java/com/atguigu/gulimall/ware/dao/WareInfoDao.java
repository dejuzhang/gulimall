package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author zhangdeju
 * @email hellodeju@gmail.com
 * @date 2023-05-17 18:04:02
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
