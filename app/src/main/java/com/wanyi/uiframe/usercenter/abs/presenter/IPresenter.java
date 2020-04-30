package com.wanyi.uiframe.usercenter.abs.presenter;

import com.wanyi.uiframe.usercenter.abs.view.IView;

public interface IPresenter<T extends IView> {

    void  attach(T view);

    T getView();

    void detach();

}
