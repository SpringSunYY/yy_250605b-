package com.lz.manage.model.vo.goodsOrderInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import com.lz.manage.model.domain.GoodsOrderInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品订单信息Vo对象 tb_goods_order_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsOrderInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    private Long orderId;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private Long goodsId;
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 支付金额
     */
    @Excel(name = "支付金额")
    private BigDecimal totalAmount;

    /**
     * 支付凭证
     */
    @Excel(name = "支付凭证")
    private String paymentCertificate;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态")
    private String status;

    /**
     * 用户
     */
    @Excel(name = "用户")
    private Long userId;
    @Excel(name = "用户")
    private String userName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;


    /**
     * 对象转封装类
     *
     * @param goodsOrderInfo GoodsOrderInfo实体对象
     * @return GoodsOrderInfoVo
     */
    public static GoodsOrderInfoVo objToVo(GoodsOrderInfo goodsOrderInfo) {
        if (goodsOrderInfo == null) {
            return null;
        }
        GoodsOrderInfoVo goodsOrderInfoVo = new GoodsOrderInfoVo();
        BeanUtils.copyProperties(goodsOrderInfo, goodsOrderInfoVo);
        return goodsOrderInfoVo;
    }
}
