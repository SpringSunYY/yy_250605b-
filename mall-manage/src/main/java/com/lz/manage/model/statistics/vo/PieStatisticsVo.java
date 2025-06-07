package com.lz.manage.model.statistics.vo;

import java.util.List;

/**
 * 饼图统计VO
 */
public class PieStatisticsVo {
    private List<String> names;

    private List<Data> data;

    public static class Data {
        private String name;
        private Long value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Data> getDates() {
        return data;
    }

    public void setDates(List<Data> data) {
        this.data = data;
    }


}
