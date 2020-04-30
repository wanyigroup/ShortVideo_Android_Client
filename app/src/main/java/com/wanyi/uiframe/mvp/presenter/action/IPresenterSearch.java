package com.wanyi.uiframe.mvp.presenter.action;

public interface IPresenterSearch extends IBasePresenter {

    /**
     * 显示搜索
     */
    void showSearch();

    /**
     * 显示初始化的视频
     */
    void showInitVideo();

    /**
     * 显示视频
     */
    void showVideo(String kewWord);

    /**
     * 清除历史
     */
    void clearHistory();

    /**
     * 刷新视频
     */
    void refreshVideoPage();

    /**
     * 加载更多
     */
    void loadMoreVideoPage();

}
