package com.wanyi.uiframe.usercenter.api.model.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginQuery {

    String account;
    String password;

}
