import request from '@/utils/request'
// 广告管理
// 新增广告图
export function addAdvList(data) {
  return request({
    url: '/system/advertising/add',
    method: 'post',
    data: data
  })
}
// 修改广告图
export function editAdvList(data) {
  return request({
    url: '/system/advertising/edit',
    method: 'post',
    data: data
  })
}
// 删除广告图
export function delAdvList(id) {
  return request({
    url: `/system/advertising/remove/${id}`,
    method: 'post'
  })
}
// 查询单个广告图
export function getInAdvList(id) {
  return request({
    url: `/system/advertising/getInfo/${id}`,
    method: 'get'
  })
}
// 模糊查询广告图
// 查询用params表示函数的参数是可变个数的
export function listAdvList(query) {
  return request({
    url: '/system/advertising/list',
    method: 'get',
    params: query
  })
}