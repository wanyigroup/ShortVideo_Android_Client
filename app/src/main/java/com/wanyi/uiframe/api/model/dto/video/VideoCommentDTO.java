package com.wanyi.uiframe.api.model.dto.video;


import com.wanyi.uiframe.comment.action.ICommentItem;

import java.util.List;

public class VideoCommentDTO {


    /**
     * code : 200
     * data : {"total":19,"per_page":15,"current_page":1,"last_page":2,"data":[{"id":23,"vid":10,"uid":1,"content":"58756","createtime":"2020-01-03 09:53:43","status":"1","nickname":"大力水手"},{"id":22,"vid":10,"uid":1,"content":"59755","createtime":"2020-01-03 09:53:32","status":"1","nickname":"大力水手"},{"id":21,"vid":10,"uid":1,"content":"5875","createtime":"2020-01-03 09:53:24","status":"1","nickname":"大力水手"},{"id":20,"vid":10,"uid":1,"content":"9985","createtime":"2020-01-03 09:47:46","status":"1","nickname":"大力水手"},{"id":19,"vid":10,"uid":1,"content":"15722","createtime":"2020-01-03 09:47:28","status":"1","nickname":"大力水手"},{"id":18,"vid":10,"uid":1,"content":"25856","createtime":"2020-01-03 09:45:54","status":"1","nickname":"大力水手"},{"id":17,"vid":10,"uid":1,"content":"5675","createtime":"2020-01-03 09:43:54","status":"1","nickname":"大力水手"},{"id":16,"vid":10,"uid":1,"content":"585","createtime":"2020-01-03 09:21:22","status":"1","nickname":"大力水手"},{"id":15,"vid":10,"uid":1,"content":"68568","createtime":"2020-01-03 09:07:31","status":"1","nickname":"大力水手"},{"id":14,"vid":10,"uid":1,"content":"路口","createtime":"2020-01-03 09:05:38","status":"1","nickname":"大力水手"},{"id":13,"vid":10,"uid":1,"content":"58855","createtime":"2020-01-03 09:03:21","status":"1","nickname":"大力水手"},{"id":12,"vid":10,"uid":1,"content":"6855","createtime":"2020-01-03 09:03:11","status":"1","nickname":"大力水手"},{"id":11,"vid":10,"uid":1,"content":"5875","createtime":"2020-01-03 08:59:27","status":"1","nickname":"大力水手"},{"id":10,"vid":10,"uid":1,"content":"688","createtime":"2020-01-03 08:59:12","status":"1","nickname":"大力水手"},{"id":9,"vid":10,"uid":1,"content":"75852","createtime":"2020-01-03 08:57:36","status":"1","nickname":"大力水手"}]}
     * msg : 成功
     */

    private int code;
    private DataBeanX data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBeanX {
        /**
         * total : 19
         * per_page : 15
         * current_page : 1
         * last_page : 2
         * data : [{"id":23,"vid":10,"uid":1,"content":"58756","createtime":"2020-01-03 09:53:43","status":"1","nickname":"大力水手"},{"id":22,"vid":10,"uid":1,"content":"59755","createtime":"2020-01-03 09:53:32","status":"1","nickname":"大力水手"},{"id":21,"vid":10,"uid":1,"content":"5875","createtime":"2020-01-03 09:53:24","status":"1","nickname":"大力水手"},{"id":20,"vid":10,"uid":1,"content":"9985","createtime":"2020-01-03 09:47:46","status":"1","nickname":"大力水手"},{"id":19,"vid":10,"uid":1,"content":"15722","createtime":"2020-01-03 09:47:28","status":"1","nickname":"大力水手"},{"id":18,"vid":10,"uid":1,"content":"25856","createtime":"2020-01-03 09:45:54","status":"1","nickname":"大力水手"},{"id":17,"vid":10,"uid":1,"content":"5675","createtime":"2020-01-03 09:43:54","status":"1","nickname":"大力水手"},{"id":16,"vid":10,"uid":1,"content":"585","createtime":"2020-01-03 09:21:22","status":"1","nickname":"大力水手"},{"id":15,"vid":10,"uid":1,"content":"68568","createtime":"2020-01-03 09:07:31","status":"1","nickname":"大力水手"},{"id":14,"vid":10,"uid":1,"content":"路口","createtime":"2020-01-03 09:05:38","status":"1","nickname":"大力水手"},{"id":13,"vid":10,"uid":1,"content":"58855","createtime":"2020-01-03 09:03:21","status":"1","nickname":"大力水手"},{"id":12,"vid":10,"uid":1,"content":"6855","createtime":"2020-01-03 09:03:11","status":"1","nickname":"大力水手"},{"id":11,"vid":10,"uid":1,"content":"5875","createtime":"2020-01-03 08:59:27","status":"1","nickname":"大力水手"},{"id":10,"vid":10,"uid":1,"content":"688","createtime":"2020-01-03 08:59:12","status":"1","nickname":"大力水手"},{"id":9,"vid":10,"uid":1,"content":"75852","createtime":"2020-01-03 08:57:36","status":"1","nickname":"大力水手"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements ICommentItem {
            /**
             * id : 23
             * vid : 10
             * uid : 1
             * content : 58756
             * createtime : 2020-01-03 09:53:43
             * status : 1
             * nickname : 大力水手
             */

            private int id;
            private int vid;
            private int uid;
            private String content;
            private String createtime;
            private String status;
            private String nickname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getVid() {
                return vid;
            }

            public void setVid(int vid) {
                this.vid = vid;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            @Override
            public String getNickName() {
                return nickname;
            }

            @Override
            public String getComment() {
                return content;
            }

            @Override
            public String getCreateTime() {
                return createtime;
            }
        }
    }
}
