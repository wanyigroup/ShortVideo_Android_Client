package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.eventbus.EUpdateEvent;
import com.wanyi.uiframe.usercenter.abs.view.IPersonalView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;

import org.greenrobot.eventbus.EventBus;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonalDataPresenter extends BasePresenter<IPersonalView> {




    /**
     * 展示个人信息
     */
    public void displayPersonalInfo() {
        UserResultDTO userResultDTO = UserPresenter.getInstance().getUserInfo();
        if(userResultDTO == null)
            return;
        String bio = userResultDTO.getBio();
        String nickName = userResultDTO.getNickName();
        String phone = userResultDTO.getMobile();
        getView().setBio(bio.isEmpty()?"暂无":bio);
        getView().setNickName(nickName);
        getView().setMobile(phone);
    }



    /**
     * 更新签名
     */
    public void updateDio() {
         UserResultDTO userResultDTO = UserPresenter.getInstance().getUserInfo();
         if(userResultDTO == null)
            return;
         getView().showDialog("更新签名",userResultDTO.getBio(),(dialog, which) -> {
             String bio = dialog.getInputEditText().getText().toString().trim();
             ApiFacade.createUserService().update_user_info(bio,null)
             .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(responseDTO -> {
                 if(responseDTO.isSuccess()) {
                     if(getView() != null) {
                         EventBus.getDefault().post(EUpdateEvent.fieldUpdate);
                         getView().setBio(bio);
                     }
                 } else {
                     if(getView() != null) {
                         getView().showMessage(responseDTO.getMsg());
                     }
                 }
             });
         });
    }

    /**
     * 更新昵称
     */
    public void updateNickname() {
         UserResultDTO userResultDTO = UserPresenter.getInstance().getUserInfo();
         if(userResultDTO == null)
             return;
         getView().showDialog("更新昵称",userResultDTO.getNickName(),(dialog, which) -> {
             String  nickName = dialog.getInputEditText().getText().toString().trim();
             ApiFacade.createUserService().update_user_info(null,nickName)
                     .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                     .subscribe(responseDTO -> {
                        if(responseDTO.isSuccess()) {
                          EventBus.getDefault().post(EUpdateEvent.fieldUpdate);
                          if(getView() != null) {
                              getView().setNickName(nickName);
                          }
                        } else {
                            if(getView() != null) {
                                getView().showMessage(responseDTO.getMsg());
                            }
                        }

                     });

         });
    }

    /**
     * 更新手机号码
     */
    public void updateMobilePhone() {
        UserResultDTO userResultDTO = UserPresenter.getInstance().getUserInfo();
        if(userResultDTO == null)
            return;
        getView().showDialog("更新手机号码",userResultDTO.getMobile(),(dialog, which) -> {

        });
    }





}
