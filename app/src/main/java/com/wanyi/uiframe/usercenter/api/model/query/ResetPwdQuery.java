package com.wanyi.uiframe.usercenter.api.model.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPwdQuery {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 新密码
     */
    private String newpassword;
    /**
     * 验证码
     */
    private String captcha;
    /**
     * 重置的类型
     */
    private String type;


}
