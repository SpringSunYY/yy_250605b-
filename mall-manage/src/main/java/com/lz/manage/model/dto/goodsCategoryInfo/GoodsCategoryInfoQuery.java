package com.lz.manage.model.dto.goodsCategoryInfo;

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
import com.lz.manage.model.domain.GoodsCategoryInfo;
/**
 * 商品分类信息Query对象 tb_goods_category_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsCategoryInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long categoryId;

    /** 父级编号 */
    private Long parentId;

    /** 分类名称 */
    private String categoryName;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param goodsCategoryInfoQuery 查询对象
     * @return GoodsCategoryInfo
     */
    public static GoodsCategoryInfo queryToObj(GoodsCategoryInfoQuery goodsCategoryInfoQuery) {
        if (goodsCategoryInfoQuery == null) {
            return null;
        }
        GoodsCategoryInfo goodsCategoryInfo = new GoodsCategoryInfo();
        BeanUtils.copyProperties(goodsCategoryInfoQuery, goodsCategoryInfo);
        return goodsCategoryInfo;
    }
}
