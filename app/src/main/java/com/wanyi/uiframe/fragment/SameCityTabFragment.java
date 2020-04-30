package com.wanyi.uiframe.fragment;


import android.os.Handler;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.alpha.XUIAlphaTextView;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "同城",anim = CoreAnim.fade)
public class SameCityTabFragment extends BaseFragment {

    @BindView(R.id.city_name)
    XUIAlphaTextView cityName;
    @BindView(R.id.city_select)
    XUIAlphaTextView citySelect;

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_samecity;
    }

    List<HotCity> hostCity = new ArrayList<>();
    @Override
    protected void initViews() {
       hostCity.add(new HotCity("上海","上海","1001"));
       hostCity.add(new HotCity("深圳","广东","1001"));
       hostCity.add(new HotCity("南京","江苏","1001"));
        ApiFacade.crateCity().subscribe( iCity -> {
           cityName.setText(iCity.getCity());
        });
    }

    @OnClick(R.id.city_select)
    public void onClick() {
        CityPicker.from(getActivity())
                .enableAnimation(true)
                .setAnimationStyle(R.style.DefaultCityPickerAnimation)
                .setLocatedCity(null)
                .setHotCities(hostCity)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                          cityName.setText(data.getName());
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CityPicker.from(getActivity()).locateComplete(new LocatedCity("安徽", "芜湖", "101280601"), LocateState.FAILURE);
                            }
                        }, 3000);
                    }
                })
                .show();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
