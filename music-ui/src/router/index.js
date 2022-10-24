import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * roles: ['admin', 'common']       // 访问路由的角色权限
 * permissions: ['a:a:a', 'b:b:b']  // 访问路由的菜单权限
 * meta : {
    noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '总览', icon: 'dashboard', affix: true }
      }
    ],
  },

  // //////// 新修改的 //////// //
  // //////////////////////////////
  // --------运营管理--------//
  //广告管理
  {
    path: '/advertisement',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'advertisement',
        component: () => import('@/views/system/advertisement/index'),
        name: 'advertisement',
        meta: { title: '广告管理advertisement' },
      },
      {
        path: 'advertisement/add-adv',
        component: () => import('@/views/system/advertisement/add-adv'),
        hidden: true,
        meta: { title: '运营管理 / 广告管理 / 添加广告管理', affix: true }
      },
      {
        path: 'advertisement/editor-adv:id',
        component: () => import('@/views/system/advertisement/editor-adv'),
        hidden: true,
        meta: { title: '编辑广告管理editor-adv', affix: true }
      },
    ]
  },

  // 订单管理order
  {
    path: '/order',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'order',
        component: () => import('@/views/system/order/index'),
        name: 'order',
        meta: { title: '订单管理order' },
      },]
  },
  // 首页管理head
  {
    path: '/head',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'head',
        component: () => import('@/views/system/head/index'),
        name: 'head',
        meta: { title: '首页管理head' },
      },]
  },
  // 优惠券管理coupons
  {
    path: '/coupons',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'coupons',
        component: () => import('@/views/system/coupons/index'),
        name: 'coupons',
        meta: { title: '优惠券管理coupons' },
      },
      {
        path: 'coupons/add-cou',
        component: () => import('@/views/system/coupons/add-cou'),
        hidden: true,
        meta: { title: '添加优惠券管理coupons', affix: true }
      },
      {
        path: 'coupons/editor-cou:id',
        component: () => import('@/views/system/coupons/editor-cou'),
        hidden: true,
        meta: { title: '编辑优惠券管理coupons', affix: true }
      },
      {
        path: 'coupons/index2',
        component: () => import('@/views/system/coupons/index2'),
        hidden: true,
        meta: { title: '优惠券使用统计', affix: true }
      },
    ]
  },
  // 优惠券发放管理coupondistribution
  {
    path: '/coupondistribution',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      // {
      //   path: 'coupondistribution',
      //   component: () => import('@/views/system/coupondistribution/index'),
      //   name: 'coupondistribution',
      //   meta: { title: '优惠券发放管理coupondistribution' },
      // },
      {
        path: 'coupondistribution/add-coup',
        component: () => import('@/views/system/coupondistribution/add-coup'),
        hidden: true,
        meta: { title: '添加优惠券发放管理coupondistribution', affix: true }
      },
    ]
  },
  //  /////////////////////////////
  // -------专辑管理--------//
  // 曲目管理
  {
    path: '/tracks',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'tracks',
        component: () => import('@/views/system/tracks/index'),
        name: 'tracks',
        meta: { title: '曲目管理tracks' },
      },
      {
        path: 'tracks/add-tack',
        component: () => import('@/views/system/tracks/add-tack'),
        hidden: true,
        meta: { title: '添加曲目管理add-tack', affix: true }
      },
      {
        path: 'tracks/editor-tack:id',
        component: () => import('@/views/system/tracks/editor-tack'),
        hidden: true,
        meta: { title: '编辑曲目管理editor-tack', affix: true }
      },
    ]
  },
  // 专辑管理
  {
    path: '/album',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'album',
        component: () => import('@/views/system/album/index'),
        name: 'album',
        meta: { title: '专辑管理album' },
      },
      {
        path: 'album/add-alb',
        component: () => import('@/views/system/album/add-alb'),
        hidden: true,
        meta: { title: '添加专辑管理add-alb', affix: true }
      },
      {
        path: 'album/editor-alb:id',
        component: () => import('@/views/system/album/editor-alb'),
        hidden: true,
        meta: { title: '编辑专辑管理editor-alb', affix: true }
      },
    ]
  },
  //  专辑分类
  {
    path: '/infrastructure',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'infrastructure',
        component: () => import('@/views/system/infrastructure/index'),
        name: 'infrastructure',
        meta: { title: '专辑分类infrastructure' },
      },
      {
        path: 'infrastructure/add-inf',
        component: () => import('@/views/system/infrastructure/add-inf'),
        hidden: true,
        meta: { title: '添加专辑分类infrastructure', affix: true }
      },
      {
        path: 'infrastructure/editor-inf',
        component: () => import('@/views/system/infrastructure/editor-inf'),
        hidden: true,
        meta: { title: '编辑专辑分类infrastructure', affix: true }
      },
      {
        path: 'infrastructure/add-infChild',
        component: () => import('@/views/system/infrastructure/add-infChild'),
        hidden: true,
        meta: { title: '添加专辑子分类infrastructure', affix: true }
      },
    ]
  },
  // ///////////////////////////////////
  //-------内容管理------// 
  // 文章管理
  {
    path: '/article',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'article',
        component: () => import('@/views/system/article/index'),
        name: 'article',
        meta: { title: '文章管理article' },
      },
      {
        path: 'article/add-art',
        component: () => import('@/views/system/article/add-art'),
        hidden: true,
        meta: { title: '添加文章管理add-art', affix: true }
      },
      {
        path: 'article/editor-art:id',
        component: () => import('@/views/system/article/editor-art'),
        hidden: true,
        meta: { title: '编辑文章管理editor-art', affix: true }
      },
    ]
  },
  // 文章分类管理
  {
    path: '/classification',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'classification',
        component: () => import('@/views/system/classification/index'),
        name: 'classification',
        meta: { title: '分类管理classification' },
      },
      {
        path: 'classification/add-cla',
        component: () => import('@/views/system/classification/add-cla'),
        hidden: true,
        meta: { title: '添加分类管理add-cla', affix: true }
      },
      {
        path: 'classification/editor-cla',
        component: () => import('@/views/system/classification/editor-cla'),
        hidden: true,
        meta: { title: '编辑分类管理editor-cla', affix: true }
      },
    ]
  },
  // 投稿管理
  {
    path: '/contribution',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'contribution',
        component: () => import('@/views/system/contribution/index'),
        name: 'contribution',
        meta: { title: '投稿管理contribution' },
      },
      {
        path: 'contribution/look-con',
        component: () => import('@/views/system/contribution/look-con'),
        hidden: true,
        meta: { title: '审核投稿管理look-con', affix: true }
      },
      {
        path: 'contribution/editor-con',
        component: () => import('@/views/system/contribution/editor-con'),
        hidden: true,
        meta: { title: '编辑投稿管理editor-con', affix: true }
      },
    ]
  },
  // 动态管理dynamic
  {
    path: '/dynamic',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'dynamic',
        component: () => import('@/views/system/dynamic/index'),
        name: 'dynamic',
        meta: { title: '动态管理dynamic' },
      },
      {
        path: 'dynamic/add-dyn',
        component: () => import('@/views/system/dynamic/add-dyn'),
        hidden: true,
        meta: { title: '添加动态管理add-dyn', affix: true }
      },
      {
        path: 'dynamic/editor-dyn:id',
        component: () => import('@/views/system/dynamic/editor-dyn'),
        hidden: true,
        meta: { title: '编辑动态管理editor-dyn', affix: true }
      },
    ]
  },
  // ///////////////////////////////////
  // -------------商城管理--------//
  // 商品管理
  {
    path: '/goods',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'goods',
        component: () => import('@/views/system/goods/index'),
        name: 'goods',
        meta: { title: '商品管理goods' },
      },
      {
        path: 'goods/add-good',
        component: () => import('@/views/system/goods/add-good'),
        hidden: true,
        meta: { title: '添加商品管理add-good', affix: true }
      },
      {
        path: 'goods/editor-good:id',
        component: () => import('@/views/system/goods/editor-good'),
        hidden: true,
        meta: { title: '编辑商品管理editor-good', affix: true }
      },
    ]
  },
  // //  商品分类goodsclass
  {
    path: '/goodsclass',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'goodsclass',
        component: () => import('@/views/system/goodsclass/index'),
        name: 'goodsclass',
        meta: { title: '分类管理goodsclass' },
      },
      {
        path: 'goodsclass/add-class',
        component: () => import('@/views/system/goodsclass/add-class'),
        hidden: true,
        meta: { title: '添加分类管理add-class', affix: true }
      },
      {
        path: 'goodsclass/editor-class',
        component: () => import('@/views/system/goodsclass/editor-class'),
        hidden: true,
        meta: { title: '编辑分类管理editor-class', affix: true }
      },
    ]
  },

  // {
  //   path: '/ClassificationGoods',
  //   component: Layout,
  //   hidden: true,
  //   redirect: 'noredirect',
  //   children: [
  //     {
  //       path: 'ClassificationGoods',
  //       component: () => import('@/views/system/ClassificationGoods/index'),
  //       name: 'ClassificationGoods',
  //       meta: { title: '商品分类ClassificationGoods' },
  //     },
  //     {
  //       path: '商品分类ClassificationGoods/add-cla',
  //       component: () => import('@/views/system/ClassificationGoods/add-cla'),
  //       hidden: true,
  //       meta: { title: '添加商品分类ClassificationGoods', affix: true }
  //     },
  //     {
  //       path: 'ClassificationGoods/editor-cla',
  //       component: () => import('@/views/system/ClassificationGoods/editor-cla'),
  //       hidden: true,
  //       meta: { title: '编辑商品分类ClassificationGoods', affix: true }
  //     },
  //   ]
  // },

  // ////////////////////////////////////
  // -------------系统管理a--------//
  // 用户管理
  {
    path: '/userss',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'userss',
        component: () => import('@/views/system/userss/index'),
        name: 'userss',
        meta: { title: '注册用户userss' },
      },
      {
        path: 'userss/add-user',
        component: () => import('@/views/system/userss/add-user'),
        hidden: true,
        meta: { title: '添加用户userss', affix: true }
      },
      // 查看用户资料
      {
        path: 'userss/look-user',
        component: () => import('@/views/system/userss/look-user'),
        hidden: true,
        meta: { title: '查看用户资料userss', affix: true }
      },
      // 编辑用户
      {
        path: 'userss/editor-user',
        component: () => import('@/views/system/userss/editor-user'),
        hidden: true,
        meta: { title: '编辑用户userss', affix: true }
      },
      // 积分等级设置
      {
        path: 'userss/level',
        component: () => import('@/views/system/userss/level'),
        hidden: true,
        meta: { title: '积分等级设置', affix: true }
      },
    ]
  },
  // 艺术家
  {
    path: '/artist',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'artist',
        component: () => import('@/views/system/artist/index'),
        name: 'artist',
        meta: { title: '艺术家artist' },
      },
      {
        path: 'artist/add-artist',
        component: () => import('@/views/system/artist/add-artist'),
        hidden: true,
        meta: { title: '添加艺术家add-artist', affix: true }
      },
      {
        path: 'artist/editor-artist',
        component: () => import('@/views/system/artist/editor-artist'),
        hidden: true,
        meta: { title: '编辑艺术家editor-artist', affix: true }
      },
    ]
  },
  // 账户管理
  {
    path: '/account',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'account',
        component: () => import('@/views/system/account/index'),
        name: 'account',
        meta: { title: '账号管理account' },
      },
      {
        path: 'account/add-account',
        component: () => import('@/views/system/account/add-account'),
        hidden: true,
        meta: { title: '添加账号管理account', affix: true }
      },
      {
        path: 'account/editor-account',
        component: () => import('@/views/system/account/editor-account'),
        hidden: true,
        meta: { title: '编辑账号管理account', affix: true }
      },
    ]
  },
  // 角色管理
  {
    path: '/roles',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'roles',
        component: () => import('@/views/system/roles/index'),
        name: 'roles',
        meta: { title: '角色管理roles' },
      },
      {
        path: 'roles/add-role',
        component: () => import('@/views/system/roles/add-role'),
        hidden: true,
        meta: { title: '添加角色管理roles', affix: true }
      },
      {
        path: 'roles/editor-role',
        component: () => import('@/views/system/roles/editor-role'),
        hidden: true,
        meta: { title: '编辑角色管理roles', affix: true }
      },
    ]
  },

  // 联系我们contactus
  {
    path: '/contactus',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'contactus',
        component: () => import('@/views/system/contactus/index'),
        name: 'contactus',
        meta: { title: '联系我们contactus' },
      },]
  },
  // 发布通知inform
  {
    path: '/inform',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'inform',
        component: () => import('@/views/system/inform/index'),
        name: 'inform',
        meta: { title: '发布通知inform' },
      },]
  },

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // 商城管理

  // 内容管理content
  // {
  //   path: '/content',
  //   component: Layout,
  //   hidden: true,
  //   redirect: 'noredirect',
  //   children: [
  //     {
  //       path: 'content',
  //       component: () => import('@/views/system/content/index'),
  //       name: 'content',
  //       meta: { title: '内容管理content' },
  //     },
  //     {
  //       path: 'content/add-cont',
  //       component: () => import('@/views/system/content/add-cont'),
  //       hidden: true,
  //       meta: { title: '添加内容管理add-cont', affix: true }
  //     },
  //     {
  //       path: 'content/editor-cont',
  //       component: () => import('@/views/system/content/editor-cont'),
  //       hidden: true,
  //       meta: { title: '编辑内容管理editor-cont', affix: true }
  //     },
  //   ]
  // },
  // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  }
]

// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: () => import('@/views/system/user/authRole'),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    permissions: ['system:dict:list'],
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    permissions: ['monitor:job:list'],
    children: [
      {
        path: 'index',
        component: () => import('@/views/monitor/job/log'),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    permissions: ['tool:gen:edit'],
    children: [
      {
        path: 'index',
        component: () => import('@/views/tool/gen/editTable'),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  }
]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
