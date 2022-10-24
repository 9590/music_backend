import request from '@/utils/request'
//  发布APP更新

//  显示当前版本号
export function getEdition(query) {
  return request({
    url: '/system/appVersion/getInfo',
    method: 'get',
    params: query
  })
}
//  发布app保存
export function appAdd(data) {
  return request({
    url: '/system/appVersion/add',
    method: 'post',
    data
  })
}