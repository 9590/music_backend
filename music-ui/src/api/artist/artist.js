//艺术家管理
import request from '@/utils/request'
//列表
export function list(query) {
    return request({
        url: '/system/appUser/list',
        method: 'get',
        params: query
    })
}
//添加
export function saveActor(data) {
    return request({
        url: '/system/appUser/saveActor',
        method: 'post',
        data: data
    })
}
//查询单个
export function getActorInfo(id) {
    return request({
        url: `/system/appUser/getActorInfo/${id}`,
        method: 'get',
    })
}
//编辑
export function updataActor(data) {
    return request({
        url: '/system/appUser/updateActor',
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