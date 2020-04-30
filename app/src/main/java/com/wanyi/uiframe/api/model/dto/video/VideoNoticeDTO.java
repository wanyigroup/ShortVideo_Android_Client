package com.wanyi.uiframe.api.model.dto.video;

import java.util.List;

public class VideoNoticeDTO {


    /**
     * code : 200
     * status : success
     * data : [{"id":1,"type":"system","content":"<p>我是一条系统消息,哦也,&nbsp; 最新优惠折扣 ,可以点击 <a href=\"http://www.baidu.com\" target=\"_blank\">http://www.baidu.com<\/a><\/p>","createtime":"0000-00-00 00:00:00","updatetime":"0000-00-00 00:00:00","deletetime":null,"status":"1"}]
     * msg : 用户未登录!
     */

    private int code;
    private String status;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : system
         * content : <p>我是一条系统消息,哦也,&nbsp; 最新优惠折扣 ,可以点击 <a href="http://www.baidu.com" target="_blank">http://www.baidu.com</a></p>
         * createtime : 0000-00-00 00:00:00
         * updatetime : 0000-00-00 00:00:00
         * deletetime : null
         * status : 1
         */

        private int id;
        private String type;
        private String content;
        private String createtime;
        private String updatetime;
        private Object deletetime;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public Object getDeletetime() {
            return deletetime;
        }

        public void setDeletetime(Object deletetime) {
            this.deletetime = deletetime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
