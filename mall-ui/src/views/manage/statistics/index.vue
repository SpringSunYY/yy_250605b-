<template>
  <div class="dashboard-editor-container">


    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <LineChartComponent :chart-data="lineData" chart-name="每日销量"/>
    </el-row>

    <el-row :gutter="32">
      <MyOnlyBarChart :bar-data="barData" barName="库存信息"/>
    </el-row>


  </div>
</template>

<script>
import LineChartComponent from '@/views/dashboard/LineChartComponent.vue'
import MyOnlyBarChart from '@/views/dashboard/MyOnlyBarChart.vue'
import { getBarStatistics, getLineStatistics } from '@/api/manage/statistics'

export default {
  name: 'Index',
  components: {
    MyOnlyBarChart,
    LineChartComponent

  },
  data() {
    return {
      barData: {
        names: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        totals: [79, 52, 200, 334, 390, 330, 220]
      },
      lineData: {
        chartXData: ['2024-10-01', '2024-10-02', '2024-10-03', '2024-10-04', '2024-10-05', '2024-10-06', '2024-10-07', '2024-10-08', '2024-10-09', '2024-10-10', '2024-10-11', '2024-10'],
        chartYData: [
          {
            value: [509, 917, 2455, 2610, 2719, 3033, 3044, 3085, 2708, 2809, 2117, 2000, 1455, 1210, 719, 733, 944, 2285, 2208, 3372, 3936, 3693, 2962, 2810, 3519, 2455, 2610, 2719, 2484, 2078],
            name: '新车销量'
          }
        ]
      }
    }
  },
  created() {
    this.getLineData()
    this.getBarData()
  },
  methods: {
    getLineData() {
      getLineStatistics().then(res => {
        this.lineData.chartXData = res.data.names
        this.lineData.chartYData[0].value = res.data.totals
        this.lineData.chartYData[0].name = '每日销量'
      })
    },
    getBarData() {
      getBarStatistics().then(res => {
        this.barData.names = res.data.names
        this.barData.values = res.data.totals
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
