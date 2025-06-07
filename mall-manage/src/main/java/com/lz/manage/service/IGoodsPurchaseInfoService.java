package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import com.lz.manage.model.vo.goodsPurchaseInfo.GoodsPurchaseInfoVo;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品进货信息Service接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface IGoodsPurchaseInfoService extends IService<GoodsPurchaseInfo>
{
    //region mybatis代码
    /**
     * 查询商品进货信息
     * 
     * @param purchaseId 商品进货信息主键
     * @return 商品进货信息
     */
    public GoodsPurchaseInfo selectGoodsPurchaseInfoByPurchaseId(Long purchaseId);

    /**
     * 查询商品进货信息列表
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 商品进货信息集合
     */
    public List<GoodsPurchaseInfo> selectGoodsPurchaseInfoList(GoodsPurchaseInfo goodsPurchaseInfo);

    /**
     * 新增商品进货信息
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    public int insertGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo);

    /**
     * 修改商品进货信息
     * 
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    public int updateGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo);

    /**
     * 批量删除商品进货信息
     * 
     * @param purchaseIds 需要删除的商品进货信息主键集合
     * @return 结果
     */
    public int deleteGoodsPurchaseInfoByPurchaseIds(Long[] purchaseIds);

    /**
     * 删除商品进货信息信息
     * 
     * @param purchaseId 商品进货信息主键
     * @return 结果
     */
    public int deleteGoodsPurchaseInfoByPurchaseId(Long purchaseId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsPurchaseInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsPurchaseInfo> getQueryWrapper(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsPurchaseInfoList GoodsPurchaseInfo集合
     * @return GoodsPurchaseInfoVO集合
     */
    List<GoodsPurchaseInfoVo> convertVoList(List<GoodsPurchaseInfo> goodsPurchaseInfoList);
}
