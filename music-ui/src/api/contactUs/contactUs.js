import request from '@/utils/request'

  //  联系我们

  //  保存
export function preservation(data) {
  return request({
    url: '/system/contactUs/addContactUs',
    method: 'post',
    data
  })
}

  //  显示初始
export function getNotices(query) {
  return request({
    url: '/system/contactUs/getInfoContactUs',
    method: 'get',
    params: query
  })
}