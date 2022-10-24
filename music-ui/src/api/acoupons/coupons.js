// 优惠券管理
import request from '@/utils/request'
// 查询整个列表
export function getCouList(data) {
    return request({
      url: '/system/coupon/list',
      method: 'get',
      params:data
    })
  }
  // 添加列表/system/coupon/add
  export function addCouList(data) {
    return request({
      url: '/system/coupon/add',
      method: 'post',
      data:data
    })
  }
  //优惠券使用统计表格数据查询 /system/coupon/selectList
  export function selCouList(data) {
    return request({
      url: '/system/coupon/operationUserCouponList',
      method: 'get',
      params:data
    })
  }
  // 修改优惠券
  export function editCouList(data) {
    return request({
      url: '/system/coupon/edit',
      method: 'post',
      data: data
    })
  }
  // 查询单个优惠券
  export function getInCouList(id) {
    return request({
      url: `/system/coupon/getInfo/${id}`,
      method: 'get'

    })
  }
  // /system/coupon/operationUserCouponList优惠券使用统计列表
  export function opeCouList(data) {
    return request({
      url: '/system/coupon/operationUserCouponList',
      method: 'get',
      params:data
    })
  }
  // 优惠券使用统计--优惠券类型/system/coupon/selectList
  export function selectListCouList(data) {
    return request({
      url: '/system/coupon/selectList',
      method: 'get',
      data:data
    })
  }
  // 优惠券管理-发放/system/coupon/addUserCoupon
  export function couCouList(data){
    return request({
      url:'/system/coupon/addUserCoupon',
      method:'post',
      data:data
    })
  }
  // 普通用户列表穿梭框/system/coupon/ordinaryUserList
  export function userCouList(data) {
    return request({
      url: '/system/coupon/ordinaryUserList',
      method: 'get',
      data:data
    })
  }

