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
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import com.lz.manage.model.vo.goodsPurchaseInfo.GoodsPurchaseInfoVo;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoQuery;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoInsert;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoEdit;
import com.lz.manage.service.IGoodsPurchaseInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 商品进货信息Controller
 *
 * @author YY
 * @date 2025-06-07
 */
@RestController
@RequestMapping("/manage/goodsPurchaseInfo")
public class GoodsPurchaseInfoController extends BaseController
{
    @Resource
    private IGoodsPurchaseInfoService goodsPurchaseInfoService;

    /**
     * 查询商品进货信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery)
    {
        GoodsPurchaseInfo goodsPurchaseInfo = GoodsPurchaseInfoQuery.queryToObj(goodsPurchaseInfoQuery);
        startPage();
        List<GoodsPurchaseInfo> list = goodsPurchaseInfoService.selectGoodsPurchaseInfoList(goodsPurchaseInfo);
        List<GoodsPurchaseInfoVo> listVo= list.stream().map(GoodsPurchaseInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出商品进货信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:export')")
    @Log(title = "商品进货信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsPurchaseInfoQuery goodsPurchaseInfoQuery)
    {
        GoodsPurchaseInfo goodsPurchaseInfo = GoodsPurchaseInfoQuery.queryToObj(goodsPurchaseInfoQuery);
        List<GoodsPurchaseInfo> list = goodsPurchaseInfoService.selectGoodsPurchaseInfoList(goodsPurchaseInfo);
        ExcelUtil<GoodsPurchaseInfo> util = new ExcelUtil<GoodsPurchaseInfo>(GoodsPurchaseInfo.class);
        util.exportExcel(response, list, "商品进货信息数据");
    }

    /**
     * 获取商品进货信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:query')")
    @GetMapping(value = "/{purchaseId}")
    public AjaxResult getInfo(@PathVariable("purchaseId") Long purchaseId)
    {
        GoodsPurchaseInfo goodsPurchaseInfo = goodsPurchaseInfoService.selectGoodsPurchaseInfoByPurchaseId(purchaseId);
        return success(GoodsPurchaseInfoVo.objToVo(goodsPurchaseInfo));
    }

    /**
     * 新增商品进货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:add')")
    @Log(title = "商品进货信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsPurchaseInfoInsert goodsPurchaseInfoInsert)
    {
        GoodsPurchaseInfo goodsPurchaseInfo = GoodsPurchaseInfoInsert.insertToObj(goodsPurchaseInfoInsert);
        return toAjax(goodsPurchaseInfoService.insertGoodsPurchaseInfo(goodsPurchaseInfo));
    }

    /**
     * 修改商品进货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:edit')")
    @Log(title = "商品进货信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsPurchaseInfoEdit goodsPurchaseInfoEdit)
    {
        GoodsPurchaseInfo goodsPurchaseInfo = GoodsPurchaseInfoEdit.editToObj(goodsPurchaseInfoEdit);
        return toAjax(goodsPurchaseInfoService.updateGoodsPurchaseInfo(goodsPurchaseInfo));
    }

    /**
     * 删除商品进货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsPurchaseInfo:remove')")
    @Log(title = "商品进货信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{purchaseIds}")
    public AjaxResult remove(@PathVariable Long[] purchaseIds)
    {
        return toAjax(goodsPurchaseInfoService.deleteGoodsPurchaseInfoByPurchaseIds(purchaseIds));
    }
}
