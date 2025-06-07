package com.lz.manage.model.dto.goodsPurchaseInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
/**
 * 商品进货信息Query对象 tb_goods_purchase_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsPurchaseInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long purchaseId;

    /** 商品编号 */
    private Long goodsId;

    /** 进货标题 */
    private String purchaseTitle;

    /** 进货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 创建人 */
    private String createBy;

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
     * @param goodsPurchaseInfoQuery 查询对象
     * @return GoodsPurchaseInfo
     */
    public static GoodsPurchaseInfo queryToObj(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery) {
        if (goodsPurchaseInfoQuery == null) {
            return null;
        }
        GoodsPurchaseInfo goodsPurchaseInfo = new GoodsPurchaseInfo();
        BeanUtils.copyProperties(goodsPurchaseInfoQuery, goodsPurchaseInfo);
        return goodsPurchaseInfo;
    }
}
