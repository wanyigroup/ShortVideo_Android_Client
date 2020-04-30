package com.wanyi.uiframe.dkplayer.fragment.factorylist;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.callback.ItemClickCallback;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.dkplayer.BaseFragment;
import com.wanyi.uiframe.dkplayer.activity.tiktok.TikTok3Activity;
import com.wanyi.uiframe.dkplayer.adapter.TikTokListAdapter;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.mvp.presenter.action.IDynamicMovieAction;
import com.wanyi.uiframe.mvp.presenter.callback.IMovieCallback;
import com.wanyi.uiframe.singleton.EnumPreMovie;

import net.arvin.itemdecorationhelper.ItemDecorationFactory;

import java.util.ArrayList;
import java.util.List;

public class FollowTikTokListFragment extends BaseFragment implements OnRefreshLoadMoreListener, IMovieCallback, ItemClickCallback {


    private List<IPreMovieVO> data = new ArrayList<>();
    private TikTokListAdapter mAdapter = new TikTokListAdapter(data);
    RecyclerView mRecyclerView;
    SmartRefreshLayout smartRefreshLayout;
    private StaggeredGridLayoutManager manager;
    private IDynamicMovieAction dynamicMovieAction;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_follow_list;
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.rv_tiktok);
        smartRefreshLayout = findViewById(R.id.smart_refresh);
        //防止刷新Item时,UI崩溃
        manager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL){
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(manager);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_emptyview,null);
        mAdapter.setEmptyView(view);
        mAdapter.setClickCallback(this);
        mRecyclerView.setAdapter(mAdapter);
        smartRefreshLayout.setOnRefreshLoadMoreListener(this);
        RecyclerView.ItemDecoration itemDecoration = new ItemDecorationFactory.DividerBuilder()
                .dividerHeight(6)
                .dividerColor(Color.BLACK)
                .showLastDivider(false)//默认是true
                .build(mRecyclerView);
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    /**
     * 获取数据源
     * @return
     */
    protected IDynamicMovieAction getDataSource() {
        return  DynamicMoviePresenter.getInstance().getDataSource(EnumPreMovie.follow);
    }

    /**
     * 设置数据源，用来跳转查看对应视频
     */
    protected void setDataSource() {
        DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.follow);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    protected void initData() {
        super.initData();
        dynamicMovieAction = getDataSource();
        dynamicMovieAction.registerCallBack(this);
        dynamicMovieAction.synData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        dynamicMovieAction.registerCallBack(this);
        dynamicMovieAction.loadMore();
        refreshLayout.finishLoadMore(2000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        dynamicMovieAction.registerCallBack(this);
        dynamicMovieAction.refresh();
        refreshLayout.finishRefresh(2000);
    }

    @Override
    public void onRefresh(List<IPreMovieVO> dataSource) {
        data.clear();
        data.addAll(dataSource);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEmpty() {
        data.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore(List<IPreMovieVO> dataSource) {
        int start = data.size();
        data.addAll(dataSource);
        mAdapter.notifyItemRangeChanged(start,dataSource.size());
    }

    @Override
    public void onComplete() {
        // Toasty.info(getContext(),"没有更多了").show();
    }

    @Override
    public void onSyn(List<IPreMovieVO> dataSource, int position) {
        data.clear();
        data.addAll(dataSource);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(position);
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void itemClick(Integer position) {
        setDataSource();
        Intent intent = new Intent(getContext(), TikTok3Activity.class);
        dynamicMovieAction.record(position);
        startActivityForResult(intent,SKIP_TO_DETAIL_REQUEST_CODE);
    }



    public static int SKIP_TO_DETAIL_REQUEST_CODE = 0x01;
    public static int DETAIL_RESULT = 0x02;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SKIP_TO_DETAIL_REQUEST_CODE && resultCode == DETAIL_RESULT) {
            dynamicMovieAction.registerCallBack(this);
            dynamicMovieAction.synData();
        }
    }


    @Override
    public void showMessage(String message) {

    }


}
