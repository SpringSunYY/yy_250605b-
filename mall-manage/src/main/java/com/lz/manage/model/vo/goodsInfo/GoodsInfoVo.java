package com.lz.manage.model.vo.goodsInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import com.lz.manage.model.domain.GoodsInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息Vo对象 tb_goods_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    private Long goodsId;

    /**
     * 分类编号
     */
    @Excel(name = "分类编号")
    private Long categoryId;

    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 商品图片
     */
    @Excel(name = "商品图片")
    private String goodsImage;

    /**
     * 商品描述
     */
    @Excel(name = "商品描述")
    private String description;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /**
     * 商品状态
     */
    @Excel(name = "商品状态")
    private String status;

    /**
     * 库存
     */
    @Excel(name = "库存")
    private Long stock;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createBy;

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
     * @param goodsInfo GoodsInfo实体对象
     * @return GoodsInfoVo
     */
    public static GoodsInfoVo objToVo(GoodsInfo goodsInfo) {
        if (goodsInfo == null) {
            return null;
        }
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        BeanUtils.copyProperties(goodsInfo, goodsInfoVo);
        return goodsInfoVo;
    }
}
