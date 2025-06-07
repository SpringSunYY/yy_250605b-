package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品进货信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-07
 */
public interface GoodsPurchaseInfoMapper extends BaseMapper<GoodsPurchaseInfo>
{
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
     * 删除商品进货信息
     * 
     * @param purchaseId 商品进货信息主键
     * @return 结果
     */
    public int deleteGoodsPurchaseInfoByPurchaseId(Long purchaseId);

    /**
     * 批量删除商品进货信息
     * 
     * @param purchaseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsPurchaseInfoByPurchaseIds(Long[] purchaseIds);
}
