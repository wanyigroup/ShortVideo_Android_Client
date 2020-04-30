package com.wanyi.uiframe;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.multidex.MultiDexApplication;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.installed.action.InstallAction;
import com.wanyi.uiframe.installed.impl.InstallFactoryImpl;
import com.wanyi.uiframe.installed.impl.InstallQueryImpl;
import com.wanyi.uiframe.mvp.presenter.CachePresenter;
import com.wanyi.uiframe.persistence.DaoMaster;
import com.wanyi.uiframe.persistence.DaoSession;
import com.wanyi.uiframe.persistence.helper.MySQLiteOpenHelper;
import com.wanyi.uiframe.upgrade.encryptor.IGnoreEncryptor;
import com.wanyi.uiframe.upgrade.http.OKHttpUpdateHttpService;
import com.wanyi.uiframe.usercenter.realize.UserPresenter;
import com.xiasuhuei321.loadingdialog.manager.StyleManager;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.XHttpSDK;
import com.xuexiang.xpage.AppPageConfig;
import com.xuexiang.xpage.PageConfig;
import com.xuexiang.xpage.PageConfiguration;
import com.xuexiang.xpage.base.XPageActivity;
import com.xuexiang.xpage.model.PageInfo;
import com.xuexiang.xrouter.launcher.XRouter;
import com.xuexiang.xui.XUI;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate.entity.UpdateError;
import com.xuexiang.xupdate.listener.OnUpdateFailureListener;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.OkHttpClient;
import static com.xuexiang.xupdate.entity.UpdateError.ERROR.CHECK_NO_NEW_VERSION;


public class MyApp extends MultiDexApplication {

    static MyApp instance = null;

    public static MyApp getInstance() {
        return instance;
    }

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.d(getClass().getName(),throwable.toString());
        });
        initXUI();
        initXPage();
        intXUpgrade();
        initHttp();
        initGreenDao();
        initRouter();
        instance = this;
        initProgress();
        initInstall();
        initCache();
//        String android = IAndroidIdUtil.getAndroidId(this);
//        UriConst.UUID = android;
        initUserOp();
        super.onCreate();
    }

    /**
     * 初始化用户操作
     */
    private void initUserOp() {
        UserPresenter.getInstance().setSp(this.getSharedPreferences("UserJson",Context.MODE_PRIVATE));
        UserPresenter.getInstance().userLoad();
    }

    private void initCache() {
        CachePresenter.getCachePresenter().attach(this);
        CachePresenter.getCachePresenter().getCacheSize();
    }

    /**
     * 安装统计
     */
    private void initInstall() {
        new InstallAction().setiInstallQuery(new InstallQueryImpl(this))
                .setInstallFactory(new InstallFactoryImpl(this))
                .execute();
    }

    private void initProgress() {
        StyleManager s = new StyleManager();
        s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);
        LoadingDialog.initStyle(s);
    }


    /**
     * 配置数据库
     */
    private void initGreenDao() {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this,"wanyi.db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    /**
     * 获取数据源
     * @return 数据源
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void initHttp() {
        ApiFacade.init();
    }

    /**
     * 初始化主题框架
     */
    private void initXUI() {
        XUI.init(this); //初始化UI框架
    }

    /**
     * 初始化跳转布局
     */
    private void initXPage() {
        PageConfig.getInstance()
                .setPageConfiguration(new PageConfiguration() { //页面注册
                    @Override
                    public List<PageInfo> registerPages(Context context) {
                        //自动注册页面,是编译时自动生成的，build一下就出来了。如果你还没使用@Page的话，暂时是不会生成的。
                        return AppPageConfig.getInstance().getPages(); //自动注册页面
                    }
                })
                .debug("PageLog")       //开启调试
                .setContainActivityClazz(XPageActivity.class) //设置默认的容器Activity
                .enableWatcher(false)   //设置是否开启内存泄露监测
                .init(this);            //初始化页面配置
    }

    /**
     * 初始化远程升级
     */
    private void intXUpgrade() {
        initXHttp();
        initOKHttpUtils();
        XUpdate.get()
                .setIFileEncryptor(new IGnoreEncryptor())
                .debug(true)
                .isWifiOnly(true)                                               //默认设置只在wifi下检查版本更新
                .isGet(true)                                                    //默认设置使用get请求检查版本
                .isAutoMode(false)                                              //默认设置非自动模式，可根据具体使用配置
                .param("versionCode", UpdateUtils.getVersionCode(this))  //设置默认公共请求参数
                .param("appKey", getPackageName())
                .setOnUpdateFailureListener(new OnUpdateFailureListener() { //设置版本更新出错的监听
                    @Override
                    public void onFailure(UpdateError error) {
                        Log.d("error",error.toString());
                        if (error.getCode() != CHECK_NO_NEW_VERSION) {
                            //对不同错误进行处理
                            Log.d("error",error.toString());
                        }
                    }
                })
                .supportSilentInstall(true)                                     //设置是否支持静默安装，默认是true
                .setIUpdateHttpService(new OKHttpUpdateHttpService())           //这个必须设置！实现网络请求功能。
                .init(this);                                          //这个必须初始化
    }

    private void initXHttp() {
        XHttpSDK.init(this);   //初始化网络请求框架，必须首先执行
        XHttpSDK.debug("XHttp");  //需要调试的时候执行
        XHttp.getInstance().setTimeout(20000);
    }

    private void initOKHttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    //初始化路由参数
    private void initRouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            XRouter.openLog();     // 打印日志
            XRouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        XRouter.init(this);
    }



}
