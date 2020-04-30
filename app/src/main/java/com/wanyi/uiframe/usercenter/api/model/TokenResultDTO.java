package com.wanyi.uiframe.usercenter.api.model;
import com.wanyi.uiframe.usercenter.api.model.action.ILoadResult;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;

public class TokenResultDTO implements ILoadResult, ITokenResult {


    /**
     * code : 1
     * msg :
     * time : 1578629167
     * data : {"token":"54d037fe-67c6-4281-8441-706dbb2289cd","expires_in":2592000}
     */
    private int code;
    private String msg;
    private int time;
    private DataBean data;

    @Override
    public boolean isSuccess() {
        return code == 1;
    }

    @Override
    public String getToken() {
        return data.getToken();
    }

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean {
        /**
         * token : 54d037fe-67c6-4281-8441-706dbb2289cd
         * expires_in : 2592000
         */

        private String token;
        private int expires_in;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
