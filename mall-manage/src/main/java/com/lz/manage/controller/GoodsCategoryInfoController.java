package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.GoodsCategoryInfo;
import com.lz.manage.model.vo.goodsCategoryInfo.GoodsCategoryInfoVo;
import com.lz.manage.model.dto.goodsCategoryInfo.GoodsCategoryInfoQuery;
import com.lz.manage.model.dto.goodsCategoryInfo.GoodsCategoryInfoInsert;
import com.lz.manage.model.dto.goodsCategoryInfo.GoodsCategoryInfoEdit;
import com.lz.manage.service.IGoodsCategoryInfoService;
import com.lz.common.utils.poi.ExcelUtil;

/**
 * 商品分类信息Controller
 *
 * @author YY
 * @date 2025-06-07
 */
@RestController
@RequestMapping("/manage/goodsCategoryInfo")
public class GoodsCategoryInfoController extends BaseController
{
    @Resource
    private IGoodsCategoryInfoService goodsCategoryInfoService;

    /**
     * 查询商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:list')")
    @GetMapping("/list")
    public AjaxResult list(GoodsCategoryInfoQuery goodsCategoryInfoQuery)
    {
        GoodsCategoryInfo goodsCategoryInfo = GoodsCategoryInfoQuery.queryToObj(goodsCategoryInfoQuery);
        List<GoodsCategoryInfo> list = goodsCategoryInfoService.selectGoodsCategoryInfoList(goodsCategoryInfo);
        List<GoodsCategoryInfoVo> listVo= list.stream().map(GoodsCategoryInfoVo::objToVo).collect(Collectors.toList());
        return success(listVo);
    }

    /**
     * 导出商品分类信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:export')")
    @Log(title = "商品分类信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsCategoryInfoQuery goodsCategoryInfoQuery)
    {
        GoodsCategoryInfo goodsCategoryInfo = GoodsCategoryInfoQuery.queryToObj(goodsCategoryInfoQuery);
        List<GoodsCategoryInfo> list = goodsCategoryInfoService.selectGoodsCategoryInfoList(goodsCategoryInfo);
        ExcelUtil<GoodsCategoryInfo> util = new ExcelUtil<GoodsCategoryInfo>(GoodsCategoryInfo.class);
        util.exportExcel(response, list, "商品分类信息数据");
    }

    /**
     * 获取商品分类信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        GoodsCategoryInfo goodsCategoryInfo = goodsCategoryInfoService.selectGoodsCategoryInfoByCategoryId(categoryId);
        return success(GoodsCategoryInfoVo.objToVo(goodsCategoryInfo));
    }

    /**
     * 新增商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:add')")
    @Log(title = "商品分类信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsCategoryInfoInsert goodsCategoryInfoInsert)
    {
        GoodsCategoryInfo goodsCategoryInfo = GoodsCategoryInfoInsert.insertToObj(goodsCategoryInfoInsert);
        return toAjax(goodsCategoryInfoService.insertGoodsCategoryInfo(goodsCategoryInfo));
    }

    /**
     * 修改商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:edit')")
    @Log(title = "商品分类信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsCategoryInfoEdit goodsCategoryInfoEdit)
    {
        GoodsCategoryInfo goodsCategoryInfo = GoodsCategoryInfoEdit.editToObj(goodsCategoryInfoEdit);
        return toAjax(goodsCategoryInfoService.updateGoodsCategoryInfo(goodsCategoryInfo));
    }

    /**
     * 删除商品分类信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCategoryInfo:remove')")
    @Log(title = "商品分类信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(goodsCategoryInfoService.deleteGoodsCategoryInfoByCategoryIds(categoryIds));
    }
}
