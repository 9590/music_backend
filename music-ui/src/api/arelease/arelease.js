import request from '@/utils/request'
// 发布通知

//  发布
export function getRelease(data) {
  return request({
    url: '/system/contactUs/addInform',
    method: 'post',
    data
  })
}

//  通知列表
export function getNotices(query) {
  return request({
    url: '/system/contactUs/informList',
    method: 'get',
    params: query
  })
}
  // 撤回
export function revoke(id) {
  return request({
    url: `/system/contactUs/revocationInform/${id}`,
    method: 'post'
  })
}