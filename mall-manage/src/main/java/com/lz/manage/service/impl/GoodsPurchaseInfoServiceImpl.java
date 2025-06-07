package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsPurchaseInfoMapper;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import com.lz.manage.service.IGoodsPurchaseInfoService;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoQuery;
import com.lz.manage.model.vo.goodsPurchaseInfo.GoodsPurchaseInfoVo;

/**
 * 商品进货信息Service业务层处理
 * 
 * @author YY
 * @date 2025-06-07
 */
@Service
public class GoodsPurchaseInfoServiceImpl extends ServiceImpl<GoodsPurchaseInfoMapper, GoodsPurchaseInfo> implements IGoodsPurchaseInfoService
{
    @Resource
    private GoodsPurchaseInfoMapper goodsPurchaseInfoMapper;

    //region mybatis代码
    /**
     * 查询商品进货信息
     * 
     * @param purchaseId 商品进货信息主键
     * @return 商品进货信息
     */
    @Override
    public GoodsPurchaseInfo selectGoodsPurchaseInfoByPurchaseId(Long purchaseId)
    {
        return goodsPurchaseInfoMapper.selectGoodsPurchaseInfoByPurchaseId(purchaseId);
    }

    /**
     * 查询商品进货信息列表
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 商品进货信息
     */
    @Override
    public List<GoodsPurchaseInfo> selectGoodsPurchaseInfoList(GoodsPurchaseInfo goodsPurchaseInfo)
    {
        return goodsPurchaseInfoMapper.selectGoodsPurchaseInfoList(goodsPurchaseInfo);
    }

    /**
     * 新增商品进货信息
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int insertGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo)
    {
        goodsPurchaseInfo.setCreateTime(DateUtils.getNowDate());
        return goodsPurchaseInfoMapper.insertGoodsPurchaseInfo(goodsPurchaseInfo);
    }

    /**
     * 修改商品进货信息
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int updateGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo)
    {
        goodsPurchaseInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsPurchaseInfoMapper.updateGoodsPurchaseInfo(goodsPurchaseInfo);
    }

    /**
     * 批量删除商品进货信息
     * 
     * @param purchaseIds 需要删除的商品进货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsPurchaseInfoByPurchaseIds(Long[] purchaseIds)
    {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseIds(purchaseIds);
    }

    /**
     * 删除商品进货信息信息
     * 
     * @param purchaseId 商品进货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsPurchaseInfoByPurchaseId(Long purchaseId)
    {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseId(purchaseId);
    }
    //endregion
    @Override
    public QueryWrapper<GoodsPurchaseInfo> getQueryWrapper(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery){
        QueryWrapper<GoodsPurchaseInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsPurchaseInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long purchaseId = goodsPurchaseInfoQuery.getPurchaseId();
        queryWrapper.eq( StringUtils.isNotNull(purchaseId),"purchase_id",purchaseId);

        Long goodsId = goodsPurchaseInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        String purchaseTitle = goodsPurchaseInfoQuery.getPurchaseTitle();
        queryWrapper.like(StringUtils.isNotEmpty(purchaseTitle) ,"purchase_title",purchaseTitle);

        Date purchaseDate = goodsPurchaseInfoQuery.getPurchaseDate();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginPurchaseDate"))&&StringUtils.isNotNull(params.get("endPurchaseDate")),"purchase_date",params.get("beginPurchaseDate"),params.get("endPurchaseDate"));

        String createBy = goodsPurchaseInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = goodsPurchaseInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsPurchaseInfoVo> convertVoList(List<GoodsPurchaseInfo> goodsPurchaseInfoList) {
        if (StringUtils.isEmpty(goodsPurchaseInfoList)) {
            return Collections.emptyList();
        }
        return goodsPurchaseInfoList.stream().map(GoodsPurchaseInfoVo::objToVo).collect(Collectors.toList());
    }

}
