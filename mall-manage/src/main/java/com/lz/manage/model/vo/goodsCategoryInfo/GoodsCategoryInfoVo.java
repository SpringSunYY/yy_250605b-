package com.lz.manage.model.vo.goodsCategoryInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.GoodsCategoryInfo;
/**
 * 商品分类信息Vo对象 tb_goods_category_info
 *
 * @author YY
 * @date 2025-06-07
 */
@Data
public class GoodsCategoryInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long categoryId;

    /** 父级编号 */
    @Excel(name = "父级编号")
    private Long parentId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

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
     * @param goodsCategoryInfo GoodsCategoryInfo实体对象
     * @return GoodsCategoryInfoVo
     */
    public static GoodsCategoryInfoVo objToVo(GoodsCategoryInfo goodsCategoryInfo) {
        if (goodsCategoryInfo == null) {
            return null;
        }
        GoodsCategoryInfoVo goodsCategoryInfoVo = new GoodsCategoryInfoVo();
        BeanUtils.copyProperties(goodsCategoryInfo, goodsCategoryInfoVo);
        return goodsCategoryInfoVo;
    }
}
