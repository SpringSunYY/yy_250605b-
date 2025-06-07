package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsCategoryInfoMapper;
import com.lz.manage.model.domain.GoodsCategoryInfo;
import com.lz.manage.service.IGoodsCategoryInfoService;
import com.lz.manage.model.dto.goodsCategoryInfo.GoodsCategoryInfoQuery;
import com.lz.manage.model.vo.goodsCategoryInfo.GoodsCategoryInfoVo;

/**
 * 商品分类信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-07
 */
@Service
public class GoodsCategoryInfoServiceImpl extends ServiceImpl<GoodsCategoryInfoMapper, GoodsCategoryInfo> implements IGoodsCategoryInfoService
{
    @Resource
    private GoodsCategoryInfoMapper goodsCategoryInfoMapper;

    //region mybatis代码
    /**
     * 查询商品分类信息
     *
     * @param categoryId 商品分类信息主键
     * @return 商品分类信息
     */
    @Override
    public GoodsCategoryInfo selectGoodsCategoryInfoByCategoryId(Long categoryId)
    {
        return goodsCategoryInfoMapper.selectGoodsCategoryInfoByCategoryId(categoryId);
    }

    /**
     * 查询商品分类信息列表
     *
     * @param goodsCategoryInfo 商品分类信息
     * @return 商品分类信息
     */
    @Override
    public List<GoodsCategoryInfo> selectGoodsCategoryInfoList(GoodsCategoryInfo goodsCategoryInfo)
    {
        return goodsCategoryInfoMapper.selectGoodsCategoryInfoList(goodsCategoryInfo);
    }

    /**
     * 新增商品分类信息
     *
     * @param goodsCategoryInfo 商品分类信息
     * @return 结果
     */
    @Override
    public int insertGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo)
    {
        goodsCategoryInfo.setCreateBy(SecurityUtils.getUsername());
        goodsCategoryInfo.setCreateTime(DateUtils.getNowDate());
        return goodsCategoryInfoMapper.insertGoodsCategoryInfo(goodsCategoryInfo);
    }

    /**
     * 修改商品分类信息
     *
     * @param goodsCategoryInfo 商品分类信息
     * @return 结果
     */
    @Override
    public int updateGoodsCategoryInfo(GoodsCategoryInfo goodsCategoryInfo)
    {
        goodsCategoryInfo.setUpdateBy(SecurityUtils.getUsername());
        goodsCategoryInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsCategoryInfoMapper.updateGoodsCategoryInfo(goodsCategoryInfo);
    }

    /**
     * 批量删除商品分类信息
     *
     * @param categoryIds 需要删除的商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryInfoByCategoryIds(Long[] categoryIds)
    {
        return goodsCategoryInfoMapper.deleteGoodsCategoryInfoByCategoryIds(categoryIds);
    }

    /**
     * 删除商品分类信息信息
     *
     * @param categoryId 商品分类信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsCategoryInfoByCategoryId(Long categoryId)
    {
        return goodsCategoryInfoMapper.deleteGoodsCategoryInfoByCategoryId(categoryId);
    }
    //endregion
    @Override
    public QueryWrapper<GoodsCategoryInfo> getQueryWrapper(GoodsCategoryInfoQuery goodsCategoryInfoQuery){
        QueryWrapper<GoodsCategoryInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsCategoryInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long categoryId = goodsCategoryInfoQuery.getCategoryId();
        queryWrapper.eq( StringUtils.isNotNull(categoryId),"category_id",categoryId);

        Long parentId = goodsCategoryInfoQuery.getParentId();
        queryWrapper.eq( StringUtils.isNotNull(parentId),"parent_id",parentId);

        String categoryName = goodsCategoryInfoQuery.getCategoryName();
        queryWrapper.like(StringUtils.isNotEmpty(categoryName) ,"category_name",categoryName);

        String createBy = goodsCategoryInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = goodsCategoryInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = goodsCategoryInfoQuery.getUpdateBy();
        queryWrapper.eq(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        return queryWrapper;
    }

    @Override
    public List<GoodsCategoryInfoVo> convertVoList(List<GoodsCategoryInfo> goodsCategoryInfoList) {
        if (StringUtils.isEmpty(goodsCategoryInfoList)) {
            return Collections.emptyList();
        }
        return goodsCategoryInfoList.stream().map(GoodsCategoryInfoVo::objToVo).collect(Collectors.toList());
    }

}
