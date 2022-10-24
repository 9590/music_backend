import request from '@/utils/request'
// 文章分类--分类管理
// 列表/system/classify/list
export function listClaList(query) {
    return request({
      url: '/system/classify/list',
      method: 'get',
      params: query
    })
  }
// 添加/system/classify/add
export function addClaList(data) {
    return request({
      url: '/system/classify/add',
      method: 'post',
      data: data
    })
  }
// 删除/system/classify/remove/{id}
export function delClaList(id) {
    return request({
      url: `/system/classify/remove/${id}`,
      method: 'post'
    })
  }
// 编辑/system/classify/edit
export function editClaList(data) {
    return request({
      url: '/system/classify/edit',
      method: 'post',
      data: data
    })
  }
// 添加专辑子类这个是专辑列表的
// 专辑分类列表这个是专辑列表的