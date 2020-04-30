package com.wanyi.uiframe.usercenter.api;

import com.wanyi.uiframe.usercenter.api.model.NoticeResultDTO;
import com.wanyi.uiframe.usercenter.api.model.TokenLoadDTO;
import com.wanyi.uiframe.usercenter.api.model.TokenResultDTO;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.ResponseDTO;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserCenterService {

    /**
     * 用户注册
     * @param requestBody
     * @return
     */
    @POST("/api/user/register")
    Observable<UserResultDTO> normalRegister(@Body RequestBody requestBody);

    /**
     * 用户登录
     * @param requestBody
     * @return
     */
    @POST("/api/user/login")
    Observable<UserResultDTO> normalLogin(@Body RequestBody requestBody);

    /**
     * 请求验证码
     * @param requestBody
     * @return
     */
    @POST("/api/sms/send")
    Observable<ResponseDTO> mobileVerify(@Body RequestBody requestBody);


    /**
     * 手机登录
     * @param requestBody 手机实体
     * @return
     */
    @POST("/api/user/mobilelogin")
    Observable<UserResultDTO> mobileLogin(@Body RequestBody requestBody);

    /**
     * 用户退出
     * @return
     */
    @GET("/api/user/logout")
    Observable<ResponseDTO> user_logout();

    /**
     * 重置密码
     * @return
     */
    @POST("/api/user/resetpwd")
    Observable<ResponseDTO> user_resetpwd(@Body RequestBody requestBody);

    /**
     * Token刷新
     * @return
     */
    @GET("/api/token/refresh")
    Observable<TokenResultDTO> token_refersh();


    /**
     * 获取token信息，检查是否有效
     * @param token 是否有效
     * @return 具体的值
     */
    @GET("/api/user/index")
    Observable<TokenLoadDTO> token_load(@Query("token") String token);


    /**
     * 拉取用户信息
     * @return
     */
    @GET("/api/user/info")
    Observable<UserResultDTO> token_user_info(@Query("token") String token);

    /**
     * 修改个人信息
     * @param bio 签名
     * @param nickname 昵称
     * @return
     */
    @POST("/api/user/profile")
    @FormUrlEncoded
    Observable<ResponseDTO> update_user_info(@Field("bio") String bio,@Field("nickname") String nickname);


    /**
     * 检测手机号码是否存在
     * @param mobile 手机号码
     * @return
     */
    @POST("/api/validate/check_mobile_exist")
    @FormUrlEncoded
    Observable<ResponseDTO> phone_check(@Field("mobile") String mobile);

    /**
     * 获取系统公告
     * @return
     */
    @GET("/api/notice")
    Observable<NoticeResultDTO> notice_list();




}
