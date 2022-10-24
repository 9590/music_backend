// 文章管理
import request from '@/utils/request'
// 文章管理-列表|模糊查询/system/article/list
export function listArticleList(query) {
  return request({
    url: '/system/article/list',
    method: 'get',
    params: query
  })
}
//   文章管理-添加/system/article/add
export function addArticleList(data) {
  return request({
    url: '/system/article/add',
    method: 'post',
    data: data
  })
}
// 删除/system/article/remove/{id}
export function delArticleList(id) {
  return request({
    url: `/system/article/remove/${id}`,
    method: 'post'
  })
}
// 置顶/system/article/updateTop/{id}
export function Topping(id) {
  return request({
    url: `/system/article/updateTop/${id}`,
    method: 'post',
    // data
  })
}
// 编辑/system/article/edit
export function editArticleList(data) {
  return request({
    url: '/system/article/edit',
    method: 'post',
    data: data
  })
}
//查询单个
export function getInfo(id) {
  return request({
    url: `/system/article/getInfo/${id}`,
    method: 'get'
  })
}