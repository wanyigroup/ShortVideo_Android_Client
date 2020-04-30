package com.wanyi.uiframe.usercenter.realize;
import android.util.Log;

import com.wanyi.uiframe.usercenter.abs.view.IResetView;
import com.wanyi.uiframe.usercenter.api.model.query.ResetPwdQuery;
import com.wanyi.uiframe.usercenter.realize.model.ResetPwdModel;

public class ResetPwdPresenter extends BasePresenter<IResetView> {

    private ResetPwdModel resetPwdModel;

    public void setResetPwdModel(ResetPwdQuery resetPwdQuery) {
        this.resetPwdModel = new ResetPwdModel(resetPwdQuery);
    }

    public void resetPwd() {
        resetPwdModel.resetPwd().subscribe(userResultDTO -> {
           if(userResultDTO.isSuccess()) {
                if(getView() != null) {
                    getView().resetSuccess();
               }
           } else {
               if(getView() != null) {
                   getView().resetFail();
                   getView().showMessage(userResultDTO.getMsg());
               }
           }
        });
    }



}
