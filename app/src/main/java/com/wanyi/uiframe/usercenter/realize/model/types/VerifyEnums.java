package com.wanyi.uiframe.usercenter.realize.model.types;

public enum VerifyEnums {

    ResetPwd("resetpwd"),
    MobileLogin("mobilelogin");

    String value;
    VerifyEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
