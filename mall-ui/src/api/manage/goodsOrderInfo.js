import request from '@/utils/request'

// 查询商品订单信息列表
export function listGoodsOrderInfo(query) {
  return request({
    url: '/manage/goodsOrderInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品订单信息详细
export function getGoodsOrderInfo(orderId) {
  return request({
    url: '/manage/goodsOrderInfo/' + orderId,
    method: 'get'
  })
}

// 新增商品订单信息
export function addGoodsOrderInfo(data) {
  return request({
    url: '/manage/goodsOrderInfo',
    method: 'post',
    data: data
  })
}

// 修改商品订单信息
export function updateGoodsOrderInfo(data) {
  return request({
    url: '/manage/goodsOrderInfo',
    method: 'put',
    data: data
  })
}

// 删除商品订单信息
export function delGoodsOrderInfo(orderId) {
  return request({
    url: '/manage/goodsOrderInfo/' + orderId,
    method: 'delete'
  })
}
