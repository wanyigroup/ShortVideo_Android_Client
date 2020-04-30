package com.wanyi.uiframe.ui;

import android.app.Activity;

import com.wanyi.uiframe.api.model.dto.advise.AppBootDTO;
import com.xuexiang.xui.widget.activity.BaseGuideActivity;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseGuideActivity {

    @Override
    protected List<Object> getGuideResourceList() {
        AppBootDTO appBootDTO = (AppBootDTO) getIntent().getSerializableExtra(SplashActivity.DATA_KEY);
        List<Object> objects = new ArrayList<>();
        objects.add(appBootDTO.getBootImage());
        return objects;
    }

    @Override
    protected Class<? extends Activity> getSkipClass() {
        return HomePageActivity.class;
    }

}
