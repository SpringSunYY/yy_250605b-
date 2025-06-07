package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsCategoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品分类信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface GoodsCategoryInfoMapper extends BaseMapper<GoodsCategoryInfo>
{
    /**
     * 查询商品分类信息
     * 
     * @param categoryId 商品分类信息主键
     * @return 商品分类信息
     */
    public GoodsCategoryInfo selectGoodsCategoryInfoByCategoryId(Long categoryId);

    /**
     * 查询商品分类信息列表
     * 
     * @param goodsCategoryInfo 商品分类信息
     * @return 商品分类信息集合
     */
    public List<GoodsCategoryInfo> selectGoodsCategoryInfoList(GoodsCategoryInfo goodsCategoryInfo);

    /**
     * 新增商品分类信息
     * 
     * @param goodsCategoryInfo 商品分类信息
     * @return 结果
     */
    public int insertGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo);

    /**
     * 修改商品分类信息
     * 
     * @param goodsCategoryInfo 商品分类信息
     * @return 结果
     */
    public int updateGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo);

    /**
     * 删除商品分类信息
     * 
     * @param categoryId 商品分类信息主键
     * @return 结果
     */
    public int deleteGoodsCategoryInfoByCategoryId(Long categoryId);

    /**
     * 批量删除商品分类信息
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsCategoryInfoByCategoryIds(Long[] categoryIds);
}
