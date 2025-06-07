package com.lz.manage.model.vo.goodsPurchaseInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
/**
 * 商品进货信息Vo对象 tb_goods_purchase_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsPurchaseInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long purchaseId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;

    /** 进货标题 */
    @Excel(name = "进货标题")
    private String purchaseTitle;

    /** 进货数量 */
    @Excel(name = "进货数量")
    private Long quantity;

    /** 进货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "进货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param goodsPurchaseInfo GoodsPurchaseInfo实体对象
     * @return GoodsPurchaseInfoVo
     */
    public static GoodsPurchaseInfoVo objToVo(GoodsPurchaseInfo goodsPurchaseInfo) {
        if (goodsPurchaseInfo == null) {
            return null;
        }
        GoodsPurchaseInfoVo goodsPurchaseInfoVo = new GoodsPurchaseInfoVo();
        BeanUtils.copyProperties(goodsPurchaseInfo, goodsPurchaseInfoVo);
        return goodsPurchaseInfoVo;
    }
}
