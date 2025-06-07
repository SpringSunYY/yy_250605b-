package com.lz.manage.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lz.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 商品订单信息对象 tb_goods_order_info
 *
 * @author YY
 * @date 2025-06-07
 */
@TableName("tb_goods_order_info")
@Data
public class GoodsOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    @TableId(value = "order_id", type = IdType.ASSIGN_ID)
    private Long orderId;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private Long goodsId;
    @TableField(exist = false)
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
    @TableField(exist = false)
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
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
