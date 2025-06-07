package com.lz.manage.model.dto.goodsOrderInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsOrderInfo;
/**
 * 商品订单信息Vo对象 tb_goods_order_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsOrderInfoEdit implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long orderId;

    /** 商品编号 */
    private Long goodsId;

    /** 支付金额 */
    private BigDecimal totalAmount;

    /** 支付凭证 */
    private String paymentCertificate;

    /** 订单状态 */
    private String status;

    /** 用户 */
    private Long userId;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsOrderInfoEdit 编辑对象
     * @return GoodsOrderInfo
     */
    public static GoodsOrderInfo editToObj(GoodsOrderInfoEdit goodsOrderInfoEdit) {
        if (goodsOrderInfoEdit == null) {
            return null;
        }
        GoodsOrderInfo goodsOrderInfo = new GoodsOrderInfo();
        BeanUtils.copyProperties(goodsOrderInfoEdit, goodsOrderInfo);
        return goodsOrderInfo;
    }
}
