package com.wanyi.uiframe.usercenter.realize;

import android.content.Context;

import com.wanyi.uiframe.usercenter.abs.model.ILoginSelectedModel;
import com.wanyi.uiframe.usercenter.abs.presenter.ILoginSelectPresenter;
import com.wanyi.uiframe.usercenter.abs.view.ILoginSelectedView;
import com.wanyi.uiframe.usercenter.realize.model.LoginSelectModel;

public class SelectLoginPresenter extends BasePresenter<ILoginSelectedView> implements ILoginSelectPresenter {

    ILoginSelectedModel selectedModel;

    public SelectLoginPresenter(Context context) {
       selectedModel = new LoginSelectModel(context);
    }


    @Override
    public void showUI() {

        if(selectedModel.getSelect() == ILoginSelectedModel.NORMAL){
            getView().showNormalLogin();
        }else {
            getView().showVerifyLogin();
        }

    }



}
