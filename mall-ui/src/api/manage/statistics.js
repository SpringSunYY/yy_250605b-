import request from '@/utils/request'

// 获取则线图
export function getLineStatistics() {
  return request({
    url: '/manage/statistics/line',
    method: 'get'
  })
}

// 获取柱状图
export function getBarStatistics() {
  return request({
    url: '/manage/statistics/bar',
    method: 'get'
  })
}
