import request from '@/utils/request'

// 查询专辑、艺人关联列表
export function list(query) {
  return request({
    url: '/system/albumAndArtist/list',
    method: 'get',
    params: query
  })
}

// 查询专辑、艺人关联详细
export function getInfo(id) {
  return request({
    url: '/system/albumAndArtist/getInfo' + id,
    method: 'get'
  })
}

// 新增专辑、艺人关联
export function add(data) {
  return request({
    url: '/system/albumAndArtist/add',
    method: 'post',
    data: data
  })
}

// 修改专辑、艺人关联
export function update(data) {
  return request({
    url: '/system/albumAndArtist/edit',
    method: 'put',
    data: data
  })
}

// 删除专辑、艺人关联
export function delArtist(id) {
  return request({
    url: '/system/albumAndArtist/remove' + id,
    method: 'delete'
  })
}
