package com.wanyi.uiframe.usercenter.realize;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.usercenter.abs.view.IRegisterView;
import com.wanyi.uiframe.usercenter.api.RequestFormBodyFactory;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.query.NormalRegisterQuery;
import com.wanyi.uiframe.usercenter.realize.subscribe.UserResultSubscribe;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<IRegisterView> {


    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @param mobile 移动电话
     */
    public void register(String username, String password, String email, String mobile ){
        NormalRegisterQuery normalRegisterQuery = NormalRegisterQuery.builder().username(username).password(password).email(email).mobile(mobile).build();
        ApiFacade.createUserService().normalRegister(RequestFormBodyFactory.create(normalRegisterQuery)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new UserResultSubscribe(){
                    @Override
                    public void successOperation(UserResultDTO userResultDTO) {
                        super.successOperation(userResultDTO);
                        if(getView() != null) {
                            getView().registerSuccess();
                        }
                    }

                    @Override
                    public void failOperation(UserResultDTO userResultDTO) {
                        super.failOperation(userResultDTO);
                        if(getView() != null) {
                            getView().showMessage(userResultDTO.getMsg());
                        }
                    }
                });
    }


}
