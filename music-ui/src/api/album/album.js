// 专辑管理
import request from '@/utils/request'
//专辑管理
//列表
export function userList(query) {
    return request({
        url: '/system/album/list',
        method: 'get',
        params: query
    })
}
//专辑分类
export function getTreeList() {
    return request({
        url: '/system/classify/treeList',
        method: 'get',
    })
}
//艺人列表
export function appUserList() {
    return request({
        url: '/system/album/appUserList',
        method: 'get'
    })
}
//歌曲列表
export function songList() {
    return request({
        url: '/system/song/songList',
        method: 'get'
    })
}
//增加
export function add(data) {
    return request({
        url: '/system/album/add',
        method: 'post',
        data: data
    })
}

//上传图片
export function addImg(data) {
  return request({
    url: '/system/album/addImg',
    method: 'post',
    data: data
  })
}
//删除
export function remove(id) {
    return request({
        url: `/system/album/remove/${id}`,
        method: 'post'
    })
}
//查询单个专辑
export function getInfo(id) {
    return request({
        url: `/system/album/getInfo/${id}`,
        method: 'get'
    })
}
//修改列表
export function edit(data) {
    return request({
        url: '/system/album/edit',
        method: 'post',
        data: data
    })
}
