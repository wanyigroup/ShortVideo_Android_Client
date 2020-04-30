package com.wanyi.uiframe.usercenter.api.model;


import android.util.Base64;

import com.wanyi.uiframe.usercenter.api.model.action.ILoadResult;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;
import com.wanyi.uiframe.usercenter.api.model.action.IUserResult;

public class UserResultDTO implements ILoadResult, ITokenResult, IUserResult {


    /**
     * code : 1
     * msg : Sign up successful
     * time : 1578534189
     * data : {"userinfo":{"id":2,"username":"testuser123","nickname":"testuser123","mobile":"18800112233","avatar":"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigyMjksMTgyLDE2MCkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgYWxpZ25tZW50LWJhc2VsaW5lPSJjZW50cmFsIj5UPC90ZXh0Pjwvc3ZnPg==","score":0,"token":"0501a1e6-cee3-4b79-8455-310ef89563fb","user_id":2,"createtime":1578534189,"expiretime":1581126189,"expires_in":2592000}}
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

    @Override
    public boolean isSuccess() {
        return code == 1;
    }

    @Override
    public String getToken() {
        return data.getUserinfo().getToken();
    }

    @Override
    public Integer getId() {
        return data.getUserinfo().getId();
    }

    @Override
    public String getNickName() {
        return data.userinfo.getNickname();
    }

    public String getUsername() {
        return data.getUserinfo().getUsername();
    }

    @Override
    public String getMobile() {
        return data.getUserinfo().getMobile();
    }

    @Override
    public String getAvatarStr() {
        String base64Str = data.userinfo.getAvatar().split(",")[1];
        byte[] normal =  Base64.decode(base64Str,Base64.DEFAULT);
        return new String(normal);
    }

    public String getAvatar() {
        return data.getUserinfo().getAvatar();
    }

    @Override
    public Integer getScore() {
        return data.getUserinfo().getScore();
    }

    @Override
    public Integer getUserId() {
        return data.getUserinfo().getUser_id();
    }

    @Override
    public Integer getLevel() {
        return data.userinfo.getLevel();
    }

    @Override
    public String getMoney() {
        return data.userinfo.getMoney();
    }

    @Override
    public String getBio() {
        return data.userinfo.bio;
    }

    public static class DataBean {
        /**
         * userinfo : {"id":2,"username":"testuser123","nickname":"testuser123","mobile":"18800112233","avatar":"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigyMjksMTgyLDE2MCkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgYWxpZ25tZW50LWJhc2VsaW5lPSJjZW50cmFsIj5UPC90ZXh0Pjwvc3ZnPg==","score":0,"token":"0501a1e6-cee3-4b79-8455-310ef89563fb","user_id":2,"createtime":1578534189,"expiretime":1581126189,"expires_in":2592000}
         */

        private UserinfoBean userinfo;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * id : 2
             * username : testuser123
             * nickname : testuser123
             * mobile : 18800112233
             * avatar : data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZlcnNpb249IjEuMSIgaGVpZ2h0PSIxMDAiIHdpZHRoPSIxMDAiPjxyZWN0IGZpbGw9InJnYigyMjksMTgyLDE2MCkiIHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj48L3JlY3Q+PHRleHQgeD0iNTAiIHk9IjUwIiBmb250LXNpemU9IjUwIiB0ZXh0LWNvcHk9ImZhc3QiIGZpbGw9IiNmZmZmZmYiIHRleHQtYW5jaG9yPSJtaWRkbGUiIHRleHQtcmlnaHRzPSJhZG1pbiIgYWxpZ25tZW50LWJhc2VsaW5lPSJjZW50cmFsIj5UPC90ZXh0Pjwvc3ZnPg==
             * score : 0
             * token : 0501a1e6-cee3-4b79-8455-310ef89563fb
             * user_id : 2
             * createtime : 1578534189
             * expiretime : 1581126189
             * expires_in : 2592000
             */

            private int id;
            private String username;
            private String nickname;
            private String mobile;
            private String avatar;
            private int score;
            private String token;
            private int user_id;
            private int createtime;
            private int expiretime;
            private int expires_in;
            private int level;
            private int vip;
            private String vipdate;
            private String bio;
            private String money;

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getVip() {
                return vip;
            }

            public void setVip(int vip) {
                this.vip = vip;
            }

            public String getVipdate() {
                return vipdate;
            }

            public void setVipdate(String vipdate) {
                this.vipdate = vipdate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(int expiretime) {
                this.expiretime = expiretime;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }

            public String getBio() {
                return bio;
            }

            public void setBio(String bio) {
                this.bio = bio;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }


}
