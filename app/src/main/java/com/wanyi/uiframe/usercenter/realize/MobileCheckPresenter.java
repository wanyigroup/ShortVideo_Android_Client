package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.usercenter.abs.view.IMobileCheckView;
import com.wanyi.uiframe.usercenter.realize.model.MobileCheckModel;

public class MobileCheckPresenter extends BasePresenter<IMobileCheckView> {


    private MobileCheckModel mobileCheckModel;

    public void setMobileCheckModel(String mobile) {
        this.mobileCheckModel = new MobileCheckModel(mobile);
    }

    public void check() {
         mobileCheckModel.check().subscribe(responseDTO -> {
             if(responseDTO.isExist()) {
                 if(getView() != null) {
                     getView().mobileExist();
                 }
             } else {
                 if(getView() != null) {
                     getView().mobileNotExist();
                 }
             }
         });

    }




}
