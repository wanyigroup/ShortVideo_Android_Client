package com.wanyi.uiframe.usercenter.api.model;

import com.wanyi.uiframe.usercenter.api.model.action.IPhoneExist;
import com.wanyi.uiframe.usercenter.api.model.action.IResponseResult;

import lombok.Data;

@Data
public class ResponseDTO implements IResponseResult, IPhoneExist {

    /**
     * code : 0
     * msg : 发送频繁
     * time : 1578536037
     * data : null
     */

    private int code;
    private String msg;
    private int time;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean isSuccess() {
        return code == 1;
    }

    @Override
    public boolean isExist() {
        return code == 1;
    }

}
