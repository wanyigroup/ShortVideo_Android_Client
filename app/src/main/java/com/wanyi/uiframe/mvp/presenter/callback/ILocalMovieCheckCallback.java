package com.wanyi.uiframe.mvp.presenter.callback;

import androidx.fragment.app.FragmentActivity;

public interface ILocalMovieCheckCallback {

    /**
     * 没有文件读取权限
     */
    void noReadPermission();

    /**
     * 获取当前activity
     * @return
     */
    FragmentActivity getActivity();

}
