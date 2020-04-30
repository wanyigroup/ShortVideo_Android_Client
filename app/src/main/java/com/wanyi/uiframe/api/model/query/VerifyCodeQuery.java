package com.wanyi.uiframe.api.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCodeQuery {
    /**
     * 邮箱
      */
    public static String TYPE_EMAIL = "Email";

    /**
     * 手机号码
     */
    public static String TYPE_SMS = "sms";

    /**
     * 手机号码或Email地址
      */
    String info;

    /**
     * 手机号码或邮箱
     */
    String type;


}
