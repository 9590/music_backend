//投稿管理
import request from '@/utils/request'
export function getlist(query) {
    return request({
        url: '/system/userContribute/list',
        method: 'get',
        params: query
    })
}
// 详情
export function getInfo(id) {
    return request({
        url: '/system/userContribute/getInfo/' + id,
        method: 'get'
    })
}
// 修改
export function edit(data) {
    return request({
        url: '/system/userContribute/edit',
        method: 'post',
        data: data
    })
}
// 审核
export function checkState(data) {
    return request({
        url: '/system/userContribute/checkState',
        method: 'post',
        data, data
    })
}