package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.GoodsPurchaseInfoMapper;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoQuery;
import com.lz.manage.model.vo.goodsPurchaseInfo.GoodsPurchaseInfoVo;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.manage.service.IGoodsPurchaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品进货信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-07
 */
@Service
public class GoodsPurchaseInfoServiceImpl extends ServiceImpl<GoodsPurchaseInfoMapper, GoodsPurchaseInfo> implements IGoodsPurchaseInfoService {
    @Resource
    private GoodsPurchaseInfoMapper goodsPurchaseInfoMapper;

    @Resource
    private IGoodsInfoService goodsInfoService;

    //region mybatis代码

    /**
     * 查询商品进货信息
     *
     * @param purchaseId 商品进货信息主键
     * @return 商品进货信息
     */
    @Override
    public GoodsPurchaseInfo selectGoodsPurchaseInfoByPurchaseId(Long purchaseId) {
        return goodsPurchaseInfoMapper.selectGoodsPurchaseInfoByPurchaseId(purchaseId);
    }

    /**
     * 查询商品进货信息列表
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 商品进货信息
     */
    @Override
    public List<GoodsPurchaseInfo> selectGoodsPurchaseInfoList(GoodsPurchaseInfo goodsPurchaseInfo) {
        List<GoodsPurchaseInfo> goodsPurchaseInfos = goodsPurchaseInfoMapper.selectGoodsPurchaseInfoList(goodsPurchaseInfo);
        for (GoodsPurchaseInfo info : goodsPurchaseInfos) {
            GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(info.getGoodsId());
            if (StringUtils.isNotNull(goodsInfo)) {
                info.setGoodsName(goodsInfo.getGoodsName());
            }
        }
        return goodsPurchaseInfos;
    }

    /**
     * 新增商品进货信息
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int insertGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo) {
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsPurchaseInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在！！！");
        }
        goodsPurchaseInfo.setCreateBy(SecurityUtils.getUsername());
        goodsPurchaseInfo.setCreateTime(DateUtils.getNowDate());
        //为商品添加库存
        goodsInfo.setStock(goodsInfo.getStock() + goodsPurchaseInfo.getQuantity());
        goodsInfoService.updateGoodsInfo(goodsInfo);
        return goodsPurchaseInfoMapper.insertGoodsPurchaseInfo(goodsPurchaseInfo);
    }

    /**
     * 修改商品进货信息
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int updateGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo) {
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsPurchaseInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo)) {
            throw new ServiceException("商品不存在！！！");
        }
        goodsPurchaseInfo.setUpdateBy(SecurityUtils.getUsername());
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
    public int deleteGoodsPurchaseInfoByPurchaseIds(Long[] purchaseIds) {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseIds(purchaseIds);
    }

    /**
     * 删除商品进货信息信息
     *
     * @param purchaseId 商品进货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsPurchaseInfoByPurchaseId(Long purchaseId) {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseId(purchaseId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsPurchaseInfo> getQueryWrapper(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery) {
        QueryWrapper<GoodsPurchaseInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsPurchaseInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long purchaseId = goodsPurchaseInfoQuery.getPurchaseId();
        queryWrapper.eq(StringUtils.isNotNull(purchaseId), "purchase_id", purchaseId);

        Long goodsId = goodsPurchaseInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        String purchaseTitle = goodsPurchaseInfoQuery.getPurchaseTitle();
        queryWrapper.like(StringUtils.isNotEmpty(purchaseTitle), "purchase_title", purchaseTitle);

        Date purchaseDate = goodsPurchaseInfoQuery.getPurchaseDate();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginPurchaseDate")) && StringUtils.isNotNull(params.get("endPurchaseDate")), "purchase_date", params.get("beginPurchaseDate"), params.get("endPurchaseDate"));

        String createBy = goodsPurchaseInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = goodsPurchaseInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

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
