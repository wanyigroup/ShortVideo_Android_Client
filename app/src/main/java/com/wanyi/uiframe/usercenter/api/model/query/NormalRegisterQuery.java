package com.wanyi.uiframe.usercenter.api.model.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NormalRegisterQuery {

    String username;
    String password;
    String email;
    String mobile;



}
