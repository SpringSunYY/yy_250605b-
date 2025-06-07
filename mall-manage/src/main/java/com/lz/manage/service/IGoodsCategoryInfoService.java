package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsCategoryInfo;
import com.lz.manage.model.vo.goodsCategoryInfo.GoodsCategoryInfoVo;
import com.lz.manage.model.dto.goodsCategoryInfo.GoodsCategoryInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品分类信息Service接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface IGoodsCategoryInfoService extends IService<GoodsCategoryInfo>
{
    //region mybatis代码
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
     * 批量删除商品分类信息
     * 
     * @param categoryIds 需要删除的商品分类信息主键集合
     * @return 结果
     */
    public int deleteGoodsCategoryInfoByCategoryIds(Long[] categoryIds);

    /**
     * 删除商品分类信息信息
     * 
     * @param categoryId 商品分类信息主键
     * @return 结果
     */
    public int deleteGoodsCategoryInfoByCategoryId(Long categoryId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsCategoryInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsCategoryInfo> getQueryWrapper(GoodsCategoryInfoQuery goodsCategoryInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsCategoryInfoList GoodsCategoryInfo集合
     * @return GoodsCategoryInfoVO集合
     */
    List<GoodsCategoryInfoVo> convertVoList(List<GoodsCategoryInfo> goodsCategoryInfoList);
}
