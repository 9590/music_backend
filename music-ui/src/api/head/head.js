//首页管理
import request from '@/utils/request'
//显示
export function list() {
    return request({
        url: '/system/operationIndex/list',
        method: 'get',
    })
}
// 歌曲列表
export function songList(query) {
    return request({
        url: "/system/song/songList",
        method: 'get',
        params: query
    })
}
//添加
export function add(data) {
    return request({
        url: '/system/operationIndex/add',
        method: 'post',
        data: data
    })
}