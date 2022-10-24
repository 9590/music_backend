// 订单管理
import request from '@/utils/request'
// 查询列表
export function getOrdList(data) {
    return request({
      url: '/system/order/list',
      method: 'get',
      params:data
    })
  }
  // 购买类型/system/order/typeList
  export function typeOrdList(data) {
    return request({
      url: '/system/order/typeList',
      method: 'get',
      data:data
    })
  }