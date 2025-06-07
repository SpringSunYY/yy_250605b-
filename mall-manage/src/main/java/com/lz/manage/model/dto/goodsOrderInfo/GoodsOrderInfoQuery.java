package com.lz.manage.model.dto.goodsOrderInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GoodsOrderInfo;
/**
 * 商品订单信息Query对象 tb_goods_order_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsOrderInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品编号 */
    private Long goodsId;

    /** 订单状态 */
    private String status;

    /** 用户 */
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param goodsOrderInfoQuery 查询对象
     * @return GoodsOrderInfo
     */
    public static GoodsOrderInfo queryToObj(GoodsOrderInfoQuery goodsOrderInfoQuery) {
        if (goodsOrderInfoQuery == null) {
            return null;
        }
        GoodsOrderInfo goodsOrderInfo = new GoodsOrderInfo();
        BeanUtils.copyProperties(goodsOrderInfoQuery, goodsOrderInfo);
        return goodsOrderInfo;
    }
}
