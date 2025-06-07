package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsOrderInfo;
import com.lz.manage.model.vo.goodsOrderInfo.GoodsOrderInfoVo;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品订单信息Service接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface IGoodsOrderInfoService extends IService<GoodsOrderInfo>
{
    //region mybatis代码
    /**
     * 查询商品订单信息
     * 
     * @param orderId 商品订单信息主键
     * @return 商品订单信息
     */
    public GoodsOrderInfo selectGoodsOrderInfoByOrderId(Long orderId);

    /**
     * 查询商品订单信息列表
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 商品订单信息集合
     */
    public List<GoodsOrderInfo> selectGoodsOrderInfoList(GoodsOrderInfo goodsOrderInfo);

    /**
     * 新增商品订单信息
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 结果
     */
    public int insertGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo);

    /**
     * 修改商品订单信息
     * 
     * @param goodsOrderInfo 商品订单信息
     * @return 结果
     */
    public int updateGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo);

    /**
     * 批量删除商品订单信息
     * 
     * @param orderIds 需要删除的商品订单信息主键集合
     * @return 结果
     */
    public int deleteGoodsOrderInfoByOrderIds(Long[] orderIds);

    /**
     * 删除商品订单信息信息
     * 
     * @param orderId 商品订单信息主键
     * @return 结果
     */
    public int deleteGoodsOrderInfoByOrderId(Long orderId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsOrderInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsOrderInfo> getQueryWrapper(GoodsOrderInfoQuery goodsOrderInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsOrderInfoList GoodsOrderInfo集合
     * @return GoodsOrderInfoVO集合
     */
    List<GoodsOrderInfoVo> convertVoList(List<GoodsOrderInfo> goodsOrderInfoList);
}
