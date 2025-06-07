package com.lz.manage.service.impl;

import com.lz.manage.mapper.StatisticsMapper;
import com.lz.manage.model.statistics.ro.StatisticsRo;
import com.lz.manage.model.statistics.vo.BarStatisticsVo;
import com.lz.manage.model.statistics.vo.LineStatisticsVo;
import com.lz.manage.service.IStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 统计
 *
 * @Project: mall
 * @Author: YY
 * @CreateTime: 2025-06-07  22:27
 * @Version: 1.0
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public LineStatisticsVo getLineStatistics() {
        List<StatisticsRo> statisticsRoList = statisticsMapper.getLineStatistics();
        LineStatisticsVo lineStatisticsVo = new LineStatisticsVo();
        ArrayList<Long> totals = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        for (StatisticsRo statisticsRo : statisticsRoList) {
            names.add(statisticsRo.getName());
            totals.add(statisticsRo.getTotal());
        }
        lineStatisticsVo.setTotals(totals);
        lineStatisticsVo.setNames(names);
        return lineStatisticsVo;
    }

    @Override
    public BarStatisticsVo getBarStatistics() {
        List<StatisticsRo> statisticsRoList = statisticsMapper.getBarStatistics();
        BarStatisticsVo barStatisticsVo = new BarStatisticsVo();
        List<Long> totals = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (StatisticsRo statisticsRo : statisticsRoList) {
            totals.add(statisticsRo.getTotal());
            names.add(statisticsRo.getName());
        }
        barStatisticsVo.setTotals(totals);
        barStatisticsVo.setNames(names);
        return barStatisticsVo;
    }
}
