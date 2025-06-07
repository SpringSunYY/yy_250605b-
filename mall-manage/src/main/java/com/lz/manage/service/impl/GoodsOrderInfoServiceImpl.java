package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsOrderInfoMapper;
import com.lz.manage.model.domain.GoodsOrderInfo;
import com.lz.manage.service.IGoodsOrderInfoService;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoQuery;
import com.lz.manage.model.vo.goodsOrderInfo.GoodsOrderInfoVo;

/**
 * 商品订单信息Service业务层处理
 * 
 * @author YY
 * @date 2025-06-07
 */
@Service
public class GoodsOrderInfoServiceImpl extends ServiceImpl<GoodsOrderInfoMapper, GoodsOrderInfo> implements IGoodsOrderInfoService
{
    @Resource
    private GoodsOrderInfoMapper goodsOrderInfoMapper;

    //region mybatis代码
    /**
     * 查询商品订单信息
     * 
     * @param orderId 商品订单信息主键
     * @return 商品订单信息
     */
    @Override
    public GoodsOrderInfo selectGoodsOrderInfoByOrderId(Long orderId)
    {
        return goodsOrderInfoMapper.selectGoodsOrderInfoByOrderId(orderId);
    }

    /**
     * 查询商品订单信息列表
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 商品订单信息
     */
    @Override
    public List<GoodsOrderInfo> selectGoodsOrderInfoList(GoodsOrderInfo goodsOrderInfo)
    {
        return goodsOrderInfoMapper.selectGoodsOrderInfoList(goodsOrderInfo);
    }

    /**
     * 新增商品订单信息
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 结果
     */
    @Override
    public int insertGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo)
    {
        goodsOrderInfo.setCreateTime(DateUtils.getNowDate());
        return goodsOrderInfoMapper.insertGoodsOrderInfo(goodsOrderInfo);
    }

    /**
     * 修改商品订单信息
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 结果
     */
    @Override
    public int updateGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo)
    {
        goodsOrderInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsOrderInfoMapper.updateGoodsOrderInfo(goodsOrderInfo);
    }

    /**
     * 批量删除商品订单信息
     * 
     * @param orderIds 需要删除的商品订单信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsOrderInfoByOrderIds(Long[] orderIds)
    {
        return goodsOrderInfoMapper.deleteGoodsOrderInfoByOrderIds(orderIds);
    }

    /**
     * 删除商品订单信息信息
     * 
     * @param orderId 商品订单信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsOrderInfoByOrderId(Long orderId)
    {
        return goodsOrderInfoMapper.deleteGoodsOrderInfoByOrderId(orderId);
    }
    //endregion
    @Override
    public QueryWrapper<GoodsOrderInfo> getQueryWrapper(GoodsOrderInfoQuery goodsOrderInfoQuery){
        QueryWrapper<GoodsOrderInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsOrderInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long goodsId = goodsOrderInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        String status = goodsOrderInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(status) ,"status",status);

        Long userId = goodsOrderInfoQuery.getUserId();
        queryWrapper.eq( StringUtils.isNotNull(userId),"user_id",userId);

        Date createTime = goodsOrderInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsOrderInfoVo> convertVoList(List<GoodsOrderInfo> goodsOrderInfoList) {
        if (StringUtils.isEmpty(goodsOrderInfoList)) {
            return Collections.emptyList();
        }
        return goodsOrderInfoList.stream().map(GoodsOrderInfoVo::objToVo).collect(Collectors.toList());
    }

}
