// 专辑管理
import request from '@/utils/request'
//专辑管理
//列表
export function userList(query) {
    return request({
        url: '/system/appUser/list',
        method: 'get',
        params: query
    })
}
//添加
export function add(data) {
    return request({
        url: '/system/appUser/add',
        method: 'post',
        data: data
    })
}
//查询单个
export function getInfo(id) {
    return request({
        url: `/system/appUser/getInfo/${id}`,
        method: 'get'
    })
}
//修改
export function edit(data) {
    return request({
        url: '/system/appUser/edit',
        method: 'post',
        data: data
    })
}
//删除
export function remove(id) {
    return request({
        url: `/system/appUser/remove/${id}`,
        method: 'post'
    })
}