import request from '@/utils/request'
// 专辑分类列表/system/classify/treeListPage
export function listInfraList(query) {
    return request({
      url: '/system/classify/treeListPage',
      method: 'get',
      params: query
    })
  }
//   添加/system/classify/add
export function addInfraList(data) {
    return request({
      url: '/system/classify/add',
      method: 'post',
      data: data
    })
  }
//   删除/system/classify/remove/{id}
export function delInfraList(id) {
    return request({
      url: `/system/classify/remove/${id}`,
      method: 'post'
    })
  }
// 编辑/system/classify/edit
export function editInfraList(data) {
    return request({
      url: '/system/classify/edit',
      method: 'post',
      data: data
    })
  }
