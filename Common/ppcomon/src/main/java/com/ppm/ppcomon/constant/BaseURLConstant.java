package com.ppm.ppcomon.constant;


import com.ppm.ppcomon.base.app.App;

/**
 * @author by lpc.
 */
public abstract class BaseURLConstant {

    // http://yan66665.gitee.io/dodopay/JSBridge.html 协议调试
    private static final String BASE_URL = "https://app.wxrrd.com/";
    private static final String BASE_TEST_URL = "http://app.weiba896.com/"; //测试环境 weiba896
    //        private static final String BASE_TEST_URL = "http://app.weiba02.com/"; // 测试环境 weiba02
    private static final String PIC_URL = "https://ms.wrcdn.com/";

    public static String getBaseUrl() {
        if (App.IS_TEST) {
            return BASE_TEST_URL;
        }
        return BASE_URL;
    }

    public static final String LOGIN_SMS_CODE = getBaseUrl() + "v1/send_message.json";
    public static final String PHONE_LOGIN = getBaseUrl() + "v1/phone_auth.json";
    public static final String WX_LOGIN = getBaseUrl() + "v1/wx_auth.json";

    public static String getPicUrl() {
        return PIC_URL;
    }

    public static String parseImageUrl(String url) {
        if (url == null)
            return "";

        if (!url.contains("https") && !url.contains("http")) {
            return getPicUrl() + url;
        }
        return url;
    }

    /**
     * baseUrl
     */
    public static final String GOODS_SKU = getBaseUrl() + "v1/goods/skudata.json";
    public static final String GOODS_ADD = getBaseUrl() + "v1/cart.json";
    public static final String SHOPPING_CART_SETTLEMENT = getBaseUrl() + "v1/cart_buy.json";
    public static final String GOODSCOMMENT = getBaseUrl() + "v1/goods_comment_list/{id}.json";
    public static final String GOODSBUYNOW = getBaseUrl() + "v1/buy_now.json";


    //首页数据，是否显示原生
    public static final String INDEX_DATA = getBaseUrl() + "v1/design/feature.json";

    //积分类型
    public static final String INTEGRAL_TYPE_URL = getBaseUrl() + "v1/credit/type.json";

    //积分列表
    public static final String INTEGRAL_RECORD_URL = getBaseUrl() + "v1/credit/record.json";

    //抵用券
    public static final String MY_CARD_LIST = getBaseUrl() + "v1/red.json";

    //优惠券
    public static final String MY_COUPON_LIST = getBaseUrl() + "v1/coupon.json";

    //我的拼团
    public static final String MY_GROUP_LIST = getBaseUrl() + "v1/pintuan.json";

    //我的竞拍
    public static final String MY_AUCTION_LIST = getBaseUrl() + "v1/my_auction_list.json";

    public static final String REFUND_EARNEST = getBaseUrl() + "v1/refund_earnest.json";

    //我的竞猜
    public static final String MY_GUESS_LIST = getBaseUrl() + "v1/my_guess.json";

    //阶梯购
    public static final String STAIR_BUY_LIST = getBaseUrl() + "v1/returncash/list.json";

    //我的推文
    public static final String MY_TWEET_LIST = getBaseUrl() + "v1/article.json";
    public static final String MY_TWEET_DETAIL = getBaseUrl() + "v1/article/{0}.json";
    public static final String MY_TWEET_SHARED = getBaseUrl() + "v1/browse/{0}.json";
    public static final String EXCHANGE_GOLD = getBaseUrl() + "v1/exchangePoint/{0}.json";

    //推客中心
    public static final String TWEETER_CENTER = getBaseUrl() + "v1/guider/center.json";

    //余额类型
    public static final String GET_BALANCE_TYPE = getBaseUrl() + "v1/balance_type.json";

    //余额列表
    public static final String GET_BALANCE_LIST = getBaseUrl() + "v1/balance.json";

    //获取个人信息
    public static final String GET_USER_INFO = getBaseUrl() + "v1/user_info.json";

    //获取用户会员卡信息
    public static final String GET_USER_CARD = getBaseUrl() + "v1/user_card.json";

    //购买会员卡列表
    public static final String GET_VIP_CARD_LIST = getBaseUrl() + "v1/buycard.json";

    //修改手机号
    public static final String MODIFY_PHONE = getBaseUrl() + "v1/edit_mobile.json";

    //设置推送开关
    public static final String CHANGE_NOTIFY_STATUS = getBaseUrl() + "v1/set_notify.json";

    //获取应用版本号
    public static final String GET_APP_VERSION = getBaseUrl() + "v1/app_version.json";

    //获取地区信息
    public static final String GET_REGION_LIST = getBaseUrl() + "v1/get_region/{parent_id}.json";

    //首页小红点
    public static final String IS_RED = getBaseUrl() + "v1/isRed.json";

    //获取七牛token
    public static final String QI_NIU_TOKEN = getBaseUrl() + "v1/qiniu_token.json";

    //修改个人信息
    public static final String MODIFY_USER_INFO = getBaseUrl() + "v1/edit_member.json";

    //退出登录
    public static final String LOGOUT = getBaseUrl() + "v1/logout";

    //绑定个推CID
    public static final String BIND_GT_CLIENT_ID = getBaseUrl() + "v1/bind_device.json";

    //会员卡下单
    public static final String CREATE_VIP_CARD_ORDER = getBaseUrl() + "v1/postviporder.json";

    //裂变模式 -- 订单详情
    public static final String TEAMFISSION_ORDER_DETAIL = getBaseUrl() + "v1/teamfission/order/{id}.json";

    //我的团队
    public static final String MY_TEAM_DATA = getBaseUrl() + "v1/teamfission/center.json";

    //我的团队成员
    public static final String MY_TEAM_MEMBER = getBaseUrl() + "v1/teamfission/members.json";

    //我的下级团队详情
    public static final String SUBORDINATE_TEAM = getBaseUrl() + "v1/teamfission/child_levels.json";

    //团队订单列表
    public static final String TEAM_ORDER_LIST = getBaseUrl() + "v1/teamfission/order.json";

    //提现
    public static final String WITHDRAW = getBaseUrl() + "v1/withdraw.json";

    //获取银行卡列表
    public static final String GET_CARD_LIST = getBaseUrl() + "v1/member_bank.json";

    //添加银行卡
    public static final String ADD_CARD = getBaseUrl() + "v1/member_bank.json";

    //获取推广模板列表
    public static final String GET_TEMPLATE_LIST = getBaseUrl() + "v1/guider/templates.json";

    //乘车海报
    public static final String GET_POSTER = getBaseUrl() + "v1/guider/template/{TEMPLATE_ID}.json";

    //删除银行卡
    public static final String DELETE_CARD = getBaseUrl() + "v1/delete_member_bank/{id}.json";

    //获取银行列表
    public static final String GET_BANK_LIST = getBaseUrl() + "v1/bank.json";

    //绑定推荐人
    public static final String BIND_REFEREE = getBaseUrl() + "v1/guider/lock.json";

    //查询推客信息
    public static final String GET_REFEREE_INFO = getBaseUrl() + "v1/guider/info.json";

    //商品详情页
    public static final String GET_GOODS_INFO = getBaseUrl() + "v1/goods/{id}.json";

    //物流详情页
    public static final String GET_LOGISTICS = getBaseUrl() + "v1/search_logistics.json";

    //蜜糖信息
    public static final String GET_SUGAR_INFO = getBaseUrl() + "v1/crystal_info.json";

    //蜜糖列表信息
    public static final String GET_SUGAR_LIST = getBaseUrl() + "v1/crystal_list.json";

    //提现页发送验证码
    public static final String WITHDRAW_SEND_MESSAGE = getBaseUrl() + "v1/app_withdraw_send_message.json"; // 增加type字段

    //推客门槛开关
    public static final String TWEETER_SWITCH = getBaseUrl() + "v1/threshold/isopen.json";

    //推客装修
    public static final String TWEETER_DESIGHN = getBaseUrl() + "v1/threshold/design.json";

    //领取礼包
    public static final String GET_GIFT = getBaseUrl() + "v1/giftbox/obtain.json";

    //查询礼包详情
    public static final String QUERY_GIFT_INFO = getBaseUrl() + "v1/giftbox/is_receive.json";

}
