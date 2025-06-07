package com.lz.manage.model.dto.goodsCategoryInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsCategoryInfo;
/**
 * 商品分类信息Vo对象 tb_goods_category_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsCategoryInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long categoryId;

    /** 父级编号 */
    private Long parentId;

    /** 分类名称 */
    private String categoryName;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsCategoryInfoInsert 插入对象
     * @return GoodsCategoryInfoInsert
     */
    public static GoodsCategoryInfo insertToObj(GoodsCategoryInfoInsert goodsCategoryInfoInsert) {
        if (goodsCategoryInfoInsert == null) {
            return null;
        }
        GoodsCategoryInfo goodsCategoryInfo = new GoodsCategoryInfo();
        BeanUtils.copyProperties(goodsCategoryInfoInsert, goodsCategoryInfo);
        return goodsCategoryInfo;
    }
}
