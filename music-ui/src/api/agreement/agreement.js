import request from '@/utils/request'

//  隐私政策及服务协议

//  保存
export function preservation(data) {
  return request({
    url: '/system/contactUs/addPrivacyRole',
    method: 'post',
    data
  })
}

//  显示初始
export function getNotices(query) {
  return request({
    url: '/system/contactUs/getInfoPrivacyRole',
    method: 'get',
    params: query
  })
}