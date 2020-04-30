package com.wanyi.uiframe.usercenter.api.model.query;

import com.wanyi.uiframe.usercenter.realize.model.types.VerifyEnums;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileVerifyQuery {

    String mobile;
    String event;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setVerifyEnums(VerifyEnums verifyEnums) {
        event = verifyEnums.getValue();
    }

    public String getEvent() {
        return event;
    }


}
