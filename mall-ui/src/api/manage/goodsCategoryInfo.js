import request from '@/utils/request'

// 查询商品分类信息列表
export function listGoodsCategoryInfo(query) {
  return request({
    url: '/manage/goodsCategoryInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品分类信息详细
export function getGoodsCategoryInfo(categoryId) {
  return request({
    url: '/manage/goodsCategoryInfo/' + categoryId,
    method: 'get'
  })
}

// 新增商品分类信息
export function addGoodsCategoryInfo(data) {
  return request({
    url: '/manage/goodsCategoryInfo',
    method: 'post',
    data: data
  })
}

// 修改商品分类信息
export function updateGoodsCategoryInfo(data) {
  return request({
    url: '/manage/goodsCategoryInfo',
    method: 'put',
    data: data
  })
}

// 删除商品分类信息
export function delGoodsCategoryInfo(categoryId) {
  return request({
    url: '/manage/goodsCategoryInfo/' + categoryId,
    method: 'delete'
  })
}
