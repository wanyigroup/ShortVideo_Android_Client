package com.wanyi.uiframe.usercenter.api.model;

import com.wanyi.uiframe.usercenter.api.model.action.ILoadResult;

public class TokenLoadDTO implements ILoadResult {


    /**
     * code : 1
     * msg :
     * time : 1578883657
     * data : {"welcome":"zhaoxiaoqiang"}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean isSuccess() {
        return code == 1;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * welcome : zhaoxiaoqiang
         */

        private String welcome;

        public String getWelcome() {
            return welcome;
        }

        public void setWelcome(String welcome) {
            this.welcome = welcome;
        }
    }
}
