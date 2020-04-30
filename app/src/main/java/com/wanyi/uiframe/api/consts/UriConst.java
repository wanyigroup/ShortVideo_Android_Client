package com.wanyi.uiframe.api.consts;

public class UriConst {


    /**
     * 用户登录的UUID
     */
    public static volatile String UUID = null;

    /**
     * 请求app域名端口
     */
    public static final String URI_APPLOADER = "http://apploader.xiong.eu";

    /**
     * 默认得域名端口
     */
    public static final String URI_DEFAULT = "http://apiv1.afuny.com";

    /**
     * 市场接口
     */
    public static volatile String URI_MARKET = "http://market.afuny.com";

    /**
     * 广告页接口
     */
    public static volatile String URI_ADVERTISE = "http://apiv1.afuny.com";

    /**
     * 支付相关网址
     */
    public static volatile String URI_H5 = "https://apiv1.afuny.com";
    /**
     * 余额充值
     */
    public static final String HTML_CHARGE = "http://hdvideo.afuny.com/payment/index/charge?";
    /**
     * 开通续费
     */
    public static final String HTML_DREDGE = URI_H5 + "/mobile/user/voucher_shop";
    /**
     * 卡券兑换
     */
    public static final String HTML_VOUCHER = URI_H5 + "/mobile/user/voucher_exchange";
    /**
     * 应用推荐
     */
    public static final String HTML_APP_RECOMMEND = URI_H5 + "/mobile/recommend";
    /**
     * 意见反馈
     */
    public static final String HTML_APP_FEEDBACK = URI_H5 + "/mobile/feedback";
    /**
     * 用户升级
     */
    public static final String HTML_USER_UPGRADE = "http://apiv1.afuny.com/mobile/user/upgrade?";


}
