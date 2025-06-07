package com.lz.manage.controller;

import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.manage.service.IStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Project: mall
 * @Author: YY
 * @CreateTime: 2025-06-07  22:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/manage/statistics")
public class StatisticsController extends BaseController {
    @Resource
    private IStatisticsService statisticsService;
    @GetMapping("/line")
    public AjaxResult getLineStatistics() {
        return success(statisticsService.getLineStatistics());
    }

    @GetMapping("/bar")
    public AjaxResult getBarStatistics() {
        return success(statisticsService.getBarStatistics());
    }
}
