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
import com.lz.manage.model.domain.GoodsOrderInfo;
import com.lz.manage.model.vo.goodsOrderInfo.GoodsOrderInfoVo;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoQuery;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoInsert;
import com.lz.manage.model.dto.goodsOrderInfo.GoodsOrderInfoEdit;
import com.lz.manage.service.IGoodsOrderInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 商品订单信息Controller
 *
 * @author YY
 * @date 2025-06-07
 */
@RestController
@RequestMapping("/manage/goodsOrderInfo")
public class GoodsOrderInfoController extends BaseController
{
    @Resource
    private IGoodsOrderInfoService goodsOrderInfoService;

    /**
     * 查询商品订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsOrderInfoQuery goodsOrderInfoQuery)
    {
        GoodsOrderInfo goodsOrderInfo = GoodsOrderInfoQuery.queryToObj(goodsOrderInfoQuery);
        startPage();
        List<GoodsOrderInfo> list = goodsOrderInfoService.selectGoodsOrderInfoList(goodsOrderInfo);
        List<GoodsOrderInfoVo> listVo= list.stream().map(GoodsOrderInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出商品订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:export')")
    @Log(title = "商品订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsOrderInfoQuery goodsOrderInfoQuery)
    {
        GoodsOrderInfo goodsOrderInfo = GoodsOrderInfoQuery.queryToObj(goodsOrderInfoQuery);
        List<GoodsOrderInfo> list = goodsOrderInfoService.selectGoodsOrderInfoList(goodsOrderInfo);
        ExcelUtil<GoodsOrderInfo> util = new ExcelUtil<GoodsOrderInfo>(GoodsOrderInfo.class);
        util.exportExcel(response, list, "商品订单信息数据");
    }

    /**
     * 获取商品订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        GoodsOrderInfo goodsOrderInfo = goodsOrderInfoService.selectGoodsOrderInfoByOrderId(orderId);
        return success(GoodsOrderInfoVo.objToVo(goodsOrderInfo));
    }

    /**
     * 新增商品订单信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:add')")
    @Log(title = "商品订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsOrderInfoInsert goodsOrderInfoInsert)
    {
        GoodsOrderInfo goodsOrderInfo = GoodsOrderInfoInsert.insertToObj(goodsOrderInfoInsert);
        return toAjax(goodsOrderInfoService.insertGoodsOrderInfo(goodsOrderInfo));
    }

    /**
     * 修改商品订单信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:edit')")
    @Log(title = "商品订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsOrderInfoEdit goodsOrderInfoEdit)
    {
        GoodsOrderInfo goodsOrderInfo = GoodsOrderInfoEdit.editToObj(goodsOrderInfoEdit);
        return toAjax(goodsOrderInfoService.updateGoodsOrderInfo(goodsOrderInfo));
    }

    /**
     * 删除商品订单信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsOrderInfo:remove')")
    @Log(title = "商品订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(goodsOrderInfoService.deleteGoodsOrderInfoByOrderIds(orderIds));
    }
}
