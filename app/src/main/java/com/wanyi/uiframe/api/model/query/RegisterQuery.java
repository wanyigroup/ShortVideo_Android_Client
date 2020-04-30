package com.wanyi.uiframe.api.model.query;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterQuery implements Serializable {

    /**
     * 手机号码，必选
     */
    String email;

    /**
     * 账户密码，必选
     */
    String password;


    /**
     * 验证码，必选
     */
    String code;


}
