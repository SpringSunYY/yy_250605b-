package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.annotation.DataScope;
import com.lz.common.core.domain.entity.SysUser;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.GoodsOrderInfoMapper;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.domain.GoodsOrderInfo;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoQuery;
import com.lz.manage.model.enums.MallOrderStatusEnum;
import com.lz.manage.model.vo.goodsOrderInfo.GoodsOrderInfoVo;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.manage.service.IGoodsOrderInfoService;
import com.lz.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品订单信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-07
 */
@Service
public class GoodsOrderInfoServiceImpl extends ServiceImpl<GoodsOrderInfoMapper, GoodsOrderInfo> implements IGoodsOrderInfoService {
    @Resource
    private GoodsOrderInfoMapper goodsOrderInfoMapper;

    @Resource
    private IGoodsInfoService goodsInfoService;

    @Resource
    private ISysUserService sysUserService;
    //region mybatis代码

    /**
     * 查询商品订单信息
     *
     * @param orderId 商品订单信息主键
     * @return 商品订单信息
     */
    @Override
    public GoodsOrderInfo selectGoodsOrderInfoByOrderId(Long orderId) {
        return goodsOrderInfoMapper.selectGoodsOrderInfoByOrderId(orderId);
    }

    /**
     * 查询商品订单信息列表
     *
     * @param goodsOrderInfo 商品订单信息
     * @return 商品订单信息
     */
    @Override
    @DataScope(deptAlias = "tb_goods_order_info", userAlias = "tb_goods_order_info")
    public List<GoodsOrderInfo> selectGoodsOrderInfoList(GoodsOrderInfo goodsOrderInfo) {
        List<GoodsOrderInfo> goodsOrderInfos = goodsOrderInfoMapper.selectGoodsOrderInfoList(goodsOrderInfo);
        for (GoodsOrderInfo info : goodsOrderInfos) {
            GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(info.getGoodsId());
            if (StringUtils.isNotNull(goodsInfo)) {
                info.setGoodsName(goodsInfo.getGoodsName());
            }
            SysUser userInfo = sysUserService.selectUserById(info.getUserId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserName(userInfo.getUserName());
            }
        }
        return goodsOrderInfos;
    }

    /**
     * 新增商品订单信息
     *
     * @param goodsOrderInfo 商品订单信息
     * @return 结果
     */
    @Override
    public int insertGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo) {
        //判断商品是否存在
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsOrderInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在！！！");
        }
        //判断库存是否充足
        if (goodsInfo.getStock() < 1) {
            throw new ServiceException("库存不足！！！");
        }
        //更新库存
        goodsInfo.setStock(goodsInfo.getStock() - 1);
        goodsInfoService.updateGoodsInfo(goodsInfo);

        goodsOrderInfo.setTotalAmount(goodsInfo.getGoodsPrice());
        goodsOrderInfo.setStatus(MallOrderStatusEnum.ORDER_STATUS_PENDING.getValue());
        goodsOrderInfo.setUserId(SecurityUtils.getUserId());
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
    public int updateGoodsOrderInfo(GoodsOrderInfo goodsOrderInfo) {
        //判断商品是否存在
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsOrderInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在！！！");
        }
        GoodsOrderInfo goodsOrderInfoDb = this.selectGoodsOrderInfoByOrderId(goodsOrderInfo.getOrderId());
        if (StringUtils.isNotNull(goodsOrderInfoDb) && goodsOrderInfoDb.getStatus().equals(MallOrderStatusEnum.ORDER_STATUS_APPROVED.getValue())) {
            throw new ServiceException("订单已审核！！！");
        }
        goodsOrderInfo.setUpdateBy(SecurityUtils.getUsername());
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
    public int deleteGoodsOrderInfoByOrderIds(Long[] orderIds) {
        return goodsOrderInfoMapper.deleteGoodsOrderInfoByOrderIds(orderIds);
    }

    /**
     * 删除商品订单信息信息
     *
     * @param orderId 商品订单信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsOrderInfoByOrderId(Long orderId) {
        return goodsOrderInfoMapper.deleteGoodsOrderInfoByOrderId(orderId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsOrderInfo> getQueryWrapper(GoodsOrderInfoQuery goodsOrderInfoQuery) {
        QueryWrapper<GoodsOrderInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsOrderInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long goodsId = goodsOrderInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        String status = goodsOrderInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(status), "status", status);

        Long userId = goodsOrderInfoQuery.getUserId();
        queryWrapper.eq(StringUtils.isNotNull(userId), "user_id", userId);

        Date createTime = goodsOrderInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

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
