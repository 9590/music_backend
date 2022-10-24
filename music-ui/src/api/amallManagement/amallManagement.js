import request from '@/utils/request'
// 商城管理

//  获取商品管理列表
export function managementList(query) {
  return request({
    url: '/system/commodity/list',
    method: 'get',
    params: query
  })
}
//  商品管理 置顶/取消置顶
export function Topping(data) {
  return request({
    url: '/system/commodity/updateTop',
    method: 'post',
    data
  })
}
//  获取下拉框分类数据
export function classIfication(query) {
  return request({
    url: '/system/classify/selectList',
    method: 'get',
    params: query
  })
}
// 删除
export function manDelete(id) {
  return request({
    url: `/system/commodity/remove/${id}`,
    method: 'post'
  })
}
//  添加
export function manAdd(data) {
  return request({
    url: '/system/commodity/add',
    method: 'post',
    data
  })
}
//  编辑查询单个
export function manEdit(id) {
  return request({
    url: `/system/commodity/getInfo/${id}`,
    method: 'get'
  })
}
//  编辑保存
export function editAdd(data) {
  return request({
    url: '/system/commodity/edit',
    method: 'post',
    data
  })
}
