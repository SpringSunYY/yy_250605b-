package com.lz.manage.mapper;

import com.lz.manage.model.statistics.ro.StatisticsRo;

import java.util.List;

public interface StatisticsMapper {
    List<StatisticsRo> getLineStatistics();

    List<StatisticsRo> getBarStatistics();
}
