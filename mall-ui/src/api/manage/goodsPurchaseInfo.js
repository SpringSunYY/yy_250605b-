import request from '@/utils/request'

// 查询商品进货信息列表
export function listGoodsPurchaseInfo(query) {
  return request({
    url: '/manage/goodsPurchaseInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品进货信息详细
export function getGoodsPurchaseInfo(purchaseId) {
  return request({
    url: '/manage/goodsPurchaseInfo/' + purchaseId,
    method: 'get'
  })
}

// 新增商品进货信息
export function addGoodsPurchaseInfo(data) {
  return request({
    url: '/manage/goodsPurchaseInfo',
    method: 'post',
    data: data
  })
}

// 修改商品进货信息
export function updateGoodsPurchaseInfo(data) {
  return request({
    url: '/manage/goodsPurchaseInfo',
    method: 'put',
    data: data
  })
}

// 删除商品进货信息
export function delGoodsPurchaseInfo(purchaseId) {
  return request({
    url: '/manage/goodsPurchaseInfo/' + purchaseId,
    method: 'delete'
  })
}
