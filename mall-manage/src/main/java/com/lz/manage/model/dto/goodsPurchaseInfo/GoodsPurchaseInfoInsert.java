package com.lz.manage.model.dto.goodsPurchaseInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
/**
 * 商品进货信息Vo对象 tb_goods_purchase_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsPurchaseInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long purchaseId;

    /** 商品编号 */
    private Long goodsId;

    /** 进货标题 */
    private String purchaseTitle;

    /** 进货数量 */
    private Long quantity;

    /** 进货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsPurchaseInfoInsert 插入对象
     * @return GoodsPurchaseInfoInsert
     */
    public static GoodsPurchaseInfo insertToObj(GoodsPurchaseInfoInsert goodsPurchaseInfoInsert) {
        if (goodsPurchaseInfoInsert == null) {
            return null;
        }
        GoodsPurchaseInfo goodsPurchaseInfo = new GoodsPurchaseInfo();
        BeanUtils.copyProperties(goodsPurchaseInfoInsert, goodsPurchaseInfo);
        return goodsPurchaseInfo;
    }
}
