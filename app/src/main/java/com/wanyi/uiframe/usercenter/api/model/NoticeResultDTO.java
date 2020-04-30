package com.wanyi.uiframe.usercenter.api.model;

import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;

import java.util.List;

public class NoticeResultDTO {


    /**
     * code : 200
     * status : success
     * data : [{"id":1,"type":"system","title":"这个是头条公告消息","content":"&lt;p&gt;我是一条系统消息,哦也,&amp;nbsp; 最新优惠折扣 ,可以点击 &lt;a href=&quot;http://www.baidu.com&quot; target=&quot;_blank&quot;&gt;http://www.baidu.com&lt;/a&gt;&lt;/p&gt;","createtime":"2020-01-16 00:00:00","updatetime":"2020-01-16 10:19:51","deletetime":null,"status":"1"}]
     * msg : 返回成功!
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

    public static class DataBean implements IAnnouncementVO {
        /**
         * id : 1
         * type : system
         * title : 这个是头条公告消息
         * content : &lt;p&gt;我是一条系统消息,哦也,&amp;nbsp; 最新优惠折扣 ,可以点击 &lt;a href=&quot;http://www.baidu.com&quot; target=&quot;_blank&quot;&gt;http://www.baidu.com&lt;/a&gt;&lt;/p&gt;
         * createtime : 2020-01-16 00:00:00
         * updatetime : 2020-01-16 10:19:51
         * deletetime : null
         * status : 1
         */

        private Long id;
        private String type;
        private String title;
        private String content;
        private String createtime;
        private String updatetime;
        private Object deletetime;
        private String status;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String getUri() {
            return null;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String getDate() {
            return createtime;
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
