package com.wanyi.uiframe.fragment.container;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;

import butterknife.BindView;

@Page(name = "系统公告", anim = CoreAnim.fade)
public class NoticeFragment extends BaseFragment implements OnRefreshLoadMoreListener {

    @BindView(R.id.smart_recycler)
    RecyclerView smartRecycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_notice;
    }

    @Override
    protected void initViews() {
       smartRefresh.setOnRefreshLoadMoreListener(this);


    }


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

}
