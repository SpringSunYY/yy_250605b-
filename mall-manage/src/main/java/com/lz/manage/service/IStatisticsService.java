package com.lz.manage.service;

import com.lz.manage.model.statistics.vo.BarStatisticsVo;
import com.lz.manage.model.statistics.vo.LineStatisticsVo;

public interface IStatisticsService {
    LineStatisticsVo getLineStatistics();

    BarStatisticsVo getBarStatistics();
}
