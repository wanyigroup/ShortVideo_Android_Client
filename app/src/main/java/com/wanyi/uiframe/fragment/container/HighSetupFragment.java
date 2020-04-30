package com.wanyi.uiframe.fragment.container;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;

@Page(name = "高级设置")
public class HighSetupFragment extends BaseFragment {

    @BindView(R.id.play_format)
    MaterialSpinner playFormat;
    @BindView(R.id.channel_select)
    MaterialSpinner channelSelect;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_high_setup;
    }

    @Override
    protected void initViews() {
        playFormat.setItems("HLS格式","MP4格式");
        channelSelect.setItems("自动判断","默认线路","美国VIP线路","香港VIP线路","韩国VIP线路","日本VIP线路");
    }





}
