//曲目管理
import request from '@/utils/request';
//列表
export function getList(query) {
    return request({
        url: '/system/song/list',
        method: 'get',
        params: query
    })
}
//添加接口
export function add(data) {
    return request({
        url: '/system/song/add',
        method: 'post',
        data: data
    })
}
//查询单个歌曲
export function getInfo(id) {
    return request({
        url: `/system/song/getInfo/${id}`,
        method: 'get'
    })
}
//修改接口
export function edit(data) {
    return request({
        url: '/system/song/edit',
        method: 'post',
        data: data
    })
}
//删除
export function remove(id) {
    return request({
        url: `/system/song/remove/${id}`,
        method: 'post'
    })
}