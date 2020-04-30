package com.wanyi.uiframe.api;



import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.api.model.dto.vo.ICity;
import com.wanyi.uiframe.api.service.IAdviseService;
import com.wanyi.uiframe.api.service.IAppLoaderService;
import com.wanyi.uiframe.api.service.IFileDownService;
import com.wanyi.uiframe.api.service.IInstallService;
import com.wanyi.uiframe.api.service.IMarketApiService;
import com.wanyi.uiframe.upgrade.IUpdateService;
import com.wanyi.uiframe.api.service.IVideoService;
import com.wanyi.uiframe.upgrade.UpgradeConst;
import com.wanyi.uiframe.usercenter.api.UserCenterService;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ApiFacade {

    /**
     * 初始化,配置各种路径
     */
    public static void init() {
        ApiFactory.createApi(UriConst.URI_APPLOADER, IAppLoaderService.class,null)
        .globalConfig().map(globalUrisDTO -> globalUrisDTO.getApi_urls())
                .subscribeOn(Schedulers.io())
                .subscribe(apiUrlsBean -> {
                     UriConst.URI_MARKET = apiUrlsBean.getBigDataDomain();
                     UriConst.URI_ADVERTISE = apiUrlsBean.getAdvDomain();
                });
    }

    /**
     * 获取广告
     * @return 广告
     */
    public static Observable<IAdviseService> createAdvise() {
         Observable observable = Observable.just(UriConst.URI_ADVERTISE).map(uri -> ApiFactory.createApi(uri,IAdviseService.class,null))
                  .onErrorResumeNext(Observable.just(ApiFactory.createApi(UriConst.URI_DEFAULT,IAdviseService.class,null)));
         return observable;
    }

    /**
     * 获取更新列表
     * @return 更新
     */
    public static Observable<IUpdateService> createUpdate() {
       Observable observable = Observable.just(UriConst.URI_DEFAULT).map(uri -> ApiFactory.createApi(uri, IUpdateService.class,null))
                .onErrorResumeNext(Observable.just(ApiFactory.createApi(UriConst.URI_DEFAULT, IUpdateService.class,null)));
       return observable;
    }


    /**
     * 创建视频相关
     * @return
     */
    public static Observable<IVideoService> createVideo() {
       Observable observable =  Observable.just(UriConst.URI_DEFAULT).map(uri -> ApiFactory.createApi(uri, IVideoService.class,UserCenterTokenFactory.createTokenClient()))
                .onErrorResumeNext(Observable.just(ApiFactory.createApi(UriConst.URI_DEFAULT,IVideoService.class,UserCenterTokenFactory.createTokenClient())));
       return observable;
    }

    /**
     * 创建市场相关
     * @return
     */
    public static Observable<IMarketApiService> crateMarket() {
        Observable observable = Observable.just(UriConst.URI_MARKET).map(uri -> ApiFactory.createApi(uri, IMarketApiService.class,OkHttpInterceptorFactory.crateMarketClient()))
                .onErrorResumeNext(Observable.just(ApiFactory.createApi(UriConst.URI_DEFAULT, IMarketApiService.class,OkHttpInterceptorFactory.crateMarketClient())));
        return observable;
    }

    /**
     * 创建文件下载服务
     * @return
     */
   public static IFileDownService createFileDown() {
       IFileDownService fileDownService = ApiFactory.createApi("www.bai.du",IFileDownService.class,null);
       return fileDownService;
   }

    /**
     * 创建用户服务
     * @return
     */
   public static UserCenterService createUserService() {
       UserCenterService userService = ApiFactory.createApi(UriConst.URI_DEFAULT, UserCenterService.class, UserCenterTokenFactory.createTokenClient());
       return userService;
   }


    /**
     * 创建用户统计的服务
     * @return
     */
   public static IInstallService createInstall() {
       IInstallService installService = ApiFactory.createApi(UriConst.URI_DEFAULT,IInstallService.class,null);
       return installService;
   }



   public static Observable<ICity> crateCity() {
      Observable<ICity> observable =  ApiFactory.createApi(UriConst.URI_DEFAULT,IVideoService.class,UserCenterTokenFactory.createTokenClient()).city().map(videosDTO -> {
           ICity iCity = videosDTO;
           return iCity;
       }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
       return observable;
   }







}
