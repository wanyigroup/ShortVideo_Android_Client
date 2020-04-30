package com.wanyi.uiframe.usercenter.realize.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.wanyi.uiframe.usercenter.abs.model.ILoginSelectedModel;

public class LoginSelectModel implements ILoginSelectedModel {

    /**
     * 选择的主键
     */
    final String SELECT_KEY = "select_key";

    SharedPreferences sp;
    public LoginSelectModel(Context context) {
       sp = context.getSharedPreferences("selectModel",Context.MODE_PRIVATE);
    }


    @Override
    public void setSelect(Integer model) {
        sp.edit().putInt(SELECT_KEY,model).commit();
    }

    @Override
    public Integer getSelect() {
        return sp.getInt(SELECT_KEY, ILoginSelectedModel.VERIFY);
    }



}
