package com.wanyi.uiframe.fragment;


import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.pixplicity.sharp.Sharp;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.aop.AopUserLogin;
import com.wanyi.uiframe.aop.impl.AspectUserLogin;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.eventbus.ELoginEvent;
import com.wanyi.uiframe.eventbus.ELoginResult;
import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.wanyi.uiframe.eventbus.ERegisterResult;
import com.wanyi.uiframe.eventbus.EUpdateEvent;
import com.wanyi.uiframe.fragment.container.CacheFragment;
import com.wanyi.uiframe.fragment.container.DataEditionFragment;
import com.wanyi.uiframe.fragment.container.HistoryFragment;
import com.wanyi.uiframe.fragment.container.LikeFragment;
import com.wanyi.uiframe.fragment.container.PersonalDataFragment;
import com.wanyi.uiframe.fragment.container.web.impl.AppRecommendWebFragment;
import com.wanyi.uiframe.fragment.container.web.impl.CouponWebFragment;
import com.wanyi.uiframe.fragment.container.web.impl.DredgeWebFragment;
import com.wanyi.uiframe.fragment.container.web.impl.FeedbackWebFragment;
import com.wanyi.uiframe.fragment.container.web.impl.RechargeFragment;
import com.wanyi.uiframe.mvp.presenter.CachePresenter;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import com.wanyi.uiframe.upgrade.UpgradeAction;
import com.wanyi.uiframe.usercenter.abs.view.IMineView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.realize.RefreshTokenSchedule;
import com.wanyi.uiframe.usercenter.realize.TokenGetPresenter;
import com.wanyi.uiframe.usercenter.realize.UserPresenter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.base.XPageFragment;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;


@Page(name = "个人中心",anim = CoreAnim.fade)
public class MineTabFragment extends BaseFragment implements OnRefreshListener, IMineView {

    @BindView(R.id.banner_image)
    SimpleImageBanner bannerImage;
    @BindView(R.id.radius_image)
    RadiusImageView radiusImage;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.role_title)
    TextView roleTitle;
    @BindView(R.id.role_content)
    TextView roleContent;
    @BindView(R.id.vip_place)
    RadiusImageView vipPlace;
    @BindView(R.id.skip_place)
    ButtonView skipPlace;
    @BindView(R.id.mine_bt_load)
    ButtonView mineBtLoad;
    @BindView(R.id.clear_cache)
    SuperTextView clearCache;




    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.disableLeftView().addAction(new TitleBar.TextAction("设置") {

            @AopUserLogin
            @Override
            public void performAction(View view) {
                openNewPage(DataEditionFragment.class);
            }
        });
        return titleBar;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_mine;
    }

    @Override
    protected void initViews() {
        loadUser();
    }


    @OnClick(R.id.mine_bt_load)
    public void loadOnclick(View v) {
        if(AspectUserLogin.getToken() == null) {
            EventBus.getDefault().post(ENotLoginEvent.NO_LOGIN);
        }else {
            showExitLoadDialog();
        }
    }

    @AopUserLogin
    @OnClick({ R.id.like_container, R.id.cache_container, R.id.history_container,  R.id.high_setup, R.id.user_security,
            R.id.my_wallet2, R.id.clear_cache, R.id.btn_upgrade,R.id.log_out,R.id.self_data,R.id.skip_place,
            R.id.app_feedback,R.id.app_recommend,R.id.coupon_container})
    public void onClick(View view) {
        Class<? extends XPageFragment> container = null;
        switch (view.getId()) {
            case R.id.skip_place:{
                container = DredgeWebFragment.class;
            }
            break;
            case R.id.coupon_container:{
                container = CouponWebFragment.class;
                }
                break;
            case R.id.like_container:
                DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.myrating);
                container = LikeFragment.class;
                break;
            case R.id.cache_container:
                DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.mycache);
                container = CacheFragment.class;
                break;
            case R.id.history_container:
                DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.history);
                container = HistoryFragment.class;
                break;
            case R.id.my_wallet2:
                container = RechargeFragment.class;
                break;
            case R.id.high_setup:
               // container = HighSetupFragment.class;
                break;
            case R.id.self_data:
                container = PersonalDataFragment.class;
                break;
            case R.id.user_security:
                //container = SecurityFragment.class;
                break;
            case R.id.clear_cache: {
                new MaterialDialog.Builder(getContext())
                        .contentColor(getResources().getColor(R.color.black))
                        .content("清空缓存")
                        .positiveText("确定")
                        .negativeText("取消")
                        .onPositive(((dialog, which) -> {
                            CachePresenter.getCachePresenter().clear();
                            updateCache();
                            Toasty.info(getContext(),"清理完成").show();
                        }))
                        .show();
            }
            break;
            case R.id.btn_upgrade: {
                UpgradeAction.showUpgradeDialog(getContext(),false);
            }
            break;
            case R.id.log_out:{
               showExitLoadDialog();
            }
            break;
            case R.id.app_feedback:{
                container = FeedbackWebFragment.class;
            }
            break;
            case R.id.app_recommend:{
                container = AppRecommendWebFragment.class;
            }
            break;
        }
        if (container != null) {
            openNewPage(container);
        }
    }

    /**
     * 退出登录
     */
    private void showExitLoadDialog() {
        new MaterialDialog.Builder(getContext())
                .contentColor(getResources().getColor(R.color.black))
                .content("退出登录?")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive(((dialog, which) -> {
                    existLoad();
                    //确定的回调
                }))
                .show();
    }


    /**
     * 退出登录
     */
    private void existLoad() {
        UserPresenter.getInstance().exitLoad();
        radiusImage.setImageResource(R.mipmap.placeholder_photo);
        setVisitorStyle();
        userName.setText("未登录");
        userId.setText("0");
        EventBus.getDefault().post(ELoginEvent.logout);
    }


    /**
     * 成功登录的标志
     */
    private void showTopAndRefresh() {
        scrollView.fullScroll(ScrollView.FOCUS_UP);
        smartRefresh.autoRefresh();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2*1000);
        loadUser();
    }

    @AopUserLogin
    private void loadUser() {
        TokenGetPresenter tokenGetPresenter = new TokenGetPresenter();
        tokenGetPresenter.attach(this);
        tokenGetPresenter.get();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeLogin(ELoginResult loginResult) {

        switch (loginResult) {
            case SUCCESS:
                showTopAndRefresh();
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeRegister(ERegisterResult registerResult) {
        showTopAndRefresh();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeUpdate(EUpdateEvent eUpdateEvent) {
        showTopAndRefresh();
    }




    @Override
    public void setUserResult(UserResultDTO userResult) {
        String nickName = userResult.getMobile();
        String uid = userResult.getUserId() + "";
        String photo = userResult.getAvatarStr();
        userName.setText(nickName);
        userId.setText(uid);
        Sharp.loadString(photo).into(radiusImage);
    }

    @Override
    public void showError(String error) {
         Toasty.error(getContext(),error).show();
    }

    @Override
    public void setVipText(String vipText) {
        roleContent.setText(vipText);
    }

    @Override
    public void finishRefresh() {
       smartRefresh.finishRefresh();
    }



    @Override
    public void setVipStyle() {
        vipPlace.setImageResource(R.mipmap.ic_vip_icon);
        roleTitle.setTextColor(getResources().getColor(R.color.vip_color));
        skipPlace.setSolidColor(getResources().getColor(R.color.vip_color));
        roleTitle.setText("尊贵的VIP会员");
        skipPlace.setText("续费");
        mineBtLoad.setText("注销");
    }

    @Override
    public void setVisitorStyle() {
        vipPlace.setImageResource(R.mipmap.ic_free_icon);
        roleTitle.setTextColor(getResources().getColor(R.color.free_color));
        skipPlace.setSolidColor(getResources().getColor(R.color.free_color));
        roleTitle.setText("未加入VIP会员");
        skipPlace.setText("开通");
        mineBtLoad.setText("登录");
    }

    @Override
    public void showLoginDialog() {
        EventBus.getDefault().post(ENotLoginEvent.NO_LOGIN);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onResume() {
        EventBus.getDefault().register(this);
        List<BannerItem> bannerItemList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BannerItem bannerItem = new BannerItem();
            bannerItem.setImgUrl("http://img1.imgtn.bdimg.com/it/u=3513613866,43987135&fm=26&gp=0.jpg");
            bannerItem.setTitle("小行");
            bannerItemList.add(bannerItem);
        }
        bannerImage.setSource(bannerItemList).startScroll();
        smartRefresh.setOnRefreshListener(this);
        smartRefresh.setEnableRefresh(true);
//        if(AspectUserLogin.getToken() != null) {
//            showTopAndRefresh();
//        }
        updateCache();
        super.onResume();
    }

    private  void updateCache() {
        clearCache.setRightString(CachePresenter.getCachePresenter().getCacheSize());
    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }



}
