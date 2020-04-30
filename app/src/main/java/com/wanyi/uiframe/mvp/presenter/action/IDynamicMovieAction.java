package com.wanyi.uiframe.mvp.presenter.action;

import com.wanyi.uiframe.mvp.presenter.callback.IMovieCallback;

public interface IDynamicMovieAction {

    /**
     * 注册监听器
     */
    void registerCallBack(IMovieCallback callback);

    /**
     * 刷新
     */
    void refresh();

    /**
     * 加载更多
     */
    void loadMore();

    /**
     * 记录位置
     * @param position 位置
     */
    void record(int position);

    /**
     * 同步数据
     */
    void synData();



}
