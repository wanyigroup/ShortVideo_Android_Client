package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.usercenter.abs.presenter.IPresenter;
import com.wanyi.uiframe.usercenter.abs.view.IView;

import java.lang.ref.WeakReference;

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected String TAG = getClass().getName();

    //弱引用，防止内存泄漏
    private WeakReference<T> weakReference;

    @Override
    public void attach(T view) {
        weakReference = new WeakReference<>(view);
    }

    @Override
    public T getView() {
        if(weakReference !=  null) {
            return weakReference.get();
        }
        return null;
    }

    @Override
    public void detach() {
        if(weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }


}
