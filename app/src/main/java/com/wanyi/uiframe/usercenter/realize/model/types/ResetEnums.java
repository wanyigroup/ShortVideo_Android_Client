package com.wanyi.uiframe.usercenter.realize.model.types;

public enum  ResetEnums {
    email("email"),
    mobile("mobile");

    private String value;
    ResetEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

