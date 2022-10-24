package com.guoguoyun.common.constant;

public class MusicCode {

    /**
     * 长期有效
     */
    public static final Integer NO_LONG_TIME = 0;
    public static final Integer YES_LONG_TIME = 1;


    /**
     * 内容管理——前端
     */
    public static final Integer CONTENT_FRONT = 1;

    /**
     * 内容管理——后台
     */
    public static final Integer CONTENT_QUEEN = 2;

    /** 用户类型  艺人 */
    public static final Integer USER_TYPE_ARTIST = 2;

    /** 用户类型  普通用户 */
    public static final Integer USER_TYPE_ORDINARY = 1;


    /** 首页 精选专辑 */
    public static final Integer INDEX_CHOICENESS_ALBUM = 1;

    /** 首页 热门曲目 */
    public static final Integer INDEX_HOT_SONG = 2;


    public static final String ERROR_NUMBER_REOETITION = "编号重复";


    /** 通知 */
    public static final Integer CONTACT_US_TYPE_INFORM = 3;

    /** 联系我们 */
    public static final Integer CONTACT_US_TYPE_CONTACT_US = 1;


    /** 隐私规则 */
    public static final Integer CONTACT_US_TYPE_PRIVACY_ROLE = 2;

    /**账号 密码登录 */
    public static final int LOGIN_ACCOUNT_PASSWORD = 1;

    /**账号 验证码登录 */
    public static final int LOGIN_ACCOUNT_CODE = 2;


    /**
     * 分类顶级父类
     */
    public static final Long CLASSIFY_PARENT = 0L;

    /**
     * 专辑分类
     */
    public static final Integer CLASSIFY_TYPE_ALBUM = 2;


    /**
     * 商品分类
     */
    public static final Integer CLASSIFY_TYPE_SHOP = 1;


    /**
     * 投稿数量-点赞
     */
    public static final Integer USER_CONTRIBUTE_RELATION_LIKE = 2;
    public static final Integer USER_CONTRIBUTE_RELATION_READ = 1;

    /**
     * 点赞
     */
    public static final Integer LIKE_YES_STATE = 1;

    /**
     * 关注
     */
    public static final Integer FOLLOW_YES_STATE = 1;

    /**
     * 支付中
     */
    public static final Integer PAY_STATUS_LOADING = 4;

    /**
     * 支付失败
     */
    public static final Integer PAY_STATUS_LOSE = 3;

    /**
     * 支付成功
     */
    public static final Integer PAY_STATUS_SUCCESS = 2;

    /**
     * 投稿类型
     */
    public static final Integer DETAIL_TYPE_CONTRIBUTE =  2;
    /**
     * 动态类型
     */
    public static final Integer DETAIL_TYPE_DYNAMICSTATE =  1;
}
