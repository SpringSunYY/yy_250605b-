package com.lz.manage.model.statistics.vo;

import java.util.List;

/**
* 主线图返回VO
 */
public class LineStatisticsVo {
   private List<String> names;

    private List<Long> totals;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Long> getTotals() {
        return totals;
    }

    public void setTotals(List<Long> totals) {
        this.totals = totals;
    }
}
