import request from '@/utils/request'

// 查询参与艺人列表
export function listArtist(query) {
  return request({
    url: '/system/artists/list',
    method: 'get',
    params: query
  })
}

// 查询参与艺人详细
export function getArtist(id) {
  return request({
    url: '/system/artists/getInfo/' + id,
    method: 'get'
  })
}

// 新增参与艺人
export function addArtist(data) {
  return request({
    url: '/system/artists/add',
    method: 'post',
    data: data
  })
}

// 修改参与艺人
export function updateArtist(data) {
  return request({
    url: '/system/artists/edit',
    method: 'put',
    data: data
  })
}

// 删除参与艺人
export function delArtist(id) {
  return request({
    url: '/system/artist/remove/' + id,
    method: 'delete'
  })
}
