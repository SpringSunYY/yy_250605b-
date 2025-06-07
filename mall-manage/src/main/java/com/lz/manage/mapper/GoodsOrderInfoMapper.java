package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsOrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品订单信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface GoodsOrderInfoMapper extends BaseMapper<GoodsOrderInfo>
{
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
     * 删除商品订单信息
     * 
     * @param orderId 商品订单信息主键
     * @return 结果
     */
    public int deleteGoodsOrderInfoByOrderId(Long orderId);

    /**
     * 批量删除商品订单信息
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsOrderInfoByOrderIds(Long[] orderIds);
}
