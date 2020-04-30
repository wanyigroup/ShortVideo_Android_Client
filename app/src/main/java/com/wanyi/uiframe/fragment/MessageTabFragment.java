package com.wanyi.uiframe.fragment;


import android.text.Spanned;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.RecyclerMessageAnnouncementAdapter;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.fragment.container.MessageFragment;
import com.wanyi.uiframe.persistence.action.INoticeVO;
import com.wanyi.uiframe.usercenter.abs.view.INoticeView;
import com.wanyi.uiframe.usercenter.realize.NoticePresenter;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import net.arvin.itemdecorationhelper.ItemDecorationFactory;

import java.util.List;

import butterknife.BindView;

@Page(name = "消息",anim = CoreAnim.fade)
public class MessageTabFragment extends BaseFragment implements INoticeView, OnRefreshListener, RecyclerMessageAnnouncementAdapter.CallBack {

    @BindView(R.id.recycler_message)
    RecyclerView recyclerMessage;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    /**
     * 适配器
     */
    RecyclerMessageAnnouncementAdapter messageAnnouncementAdapter = new RecyclerMessageAnnouncementAdapter();
    /**
     * 关注列表
     */
    NoticePresenter noticePresenter = new NoticePresenter();

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle();
        titleBar.disableLeftView().setTitle("消息").addAction(new TitleBar.TextAction("设置") {
            @Override
            public void performAction(View view) {

            }
        });
        return titleBar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_message;
    }

    @Override
    protected void initViews() {
        noticePresenter.attach(this);
        recyclerMessage.setAdapter(messageAnnouncementAdapter);
        messageAnnouncementAdapter.setCallBack(this);
        noticePresenter.localLoad();
        recyclerMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new ItemDecorationFactory.DividerBuilder()
                .dividerHeight(1)
                .dividerColor(getResources().getColor(R.color.cp_color_gray_light))
                .showLastDivider(false)//默认是true
                .build(recyclerMessage);
        recyclerMessage.addItemDecoration(itemDecoration);
        smartRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void finishRefresh() {
       smartRefreshLayout.finishRefresh();
    }

    @Override
    public void loadData(List<INoticeVO> data) {
        messageAnnouncementAdapter.getData().clear();
        messageAnnouncementAdapter.getData().addAll(data);
        messageAnnouncementAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        noticePresenter.refreshLoad();
    }

    @Override
    public void clickMessage(String title, Spanned html, INoticeVO iNoticeVO) {
        noticePresenter.clickMessage(iNoticeVO);
        MessageFragment.TITLE = title;
        MessageFragment.CONTENT = html;
        openNewPage(MessageFragment.class);
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
