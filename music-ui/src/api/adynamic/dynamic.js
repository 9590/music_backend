// 动态管理
import request from '@/utils/request'

// 列表/system/dynamicstate/list
export function listDynList(query) {
    return request({
      url: '/system/dynamicstate/list',
      method: 'get',
      params: query
    })
}

// 添加/system/dynamicstate/add
export function addDynList(data) {
  return request({
    url: '/system/dynamicstate/add',
    method: 'post',
    data: data
  })
}

// 删除/system/dynamicstate/remove/{id}
export function delDynList(id) {
  return request({
    url: `/system/dynamicstate/remove/${id}`,
    method: 'post'
  })
}

// 修改/system/dynamicstate/edit
export function editDynList(data) {
  return request({
    url: '/system/dynamicstate/edit',
    method: 'post',
    data: data
  })
}

// 查询单个/system/dynamicstate/getInfo/{id}
export function getDynList(id) {
  return request({
    url: `/system/dynamicstate/getInfo/${id}`,
    method: 'get'
  })
}

// 选择艺术家/system/album/appUserList
export function UserDynList(data) {
  return request({
    url: '/system/album/appUserList',
    method: 'get',
   data:data
  })
}