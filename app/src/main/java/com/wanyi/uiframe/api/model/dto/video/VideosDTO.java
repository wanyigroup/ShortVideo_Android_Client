package com.wanyi.uiframe.api.model.dto.video;

import com.wanyi.uiframe.api.model.dto.vo.ICity;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;

import java.util.List;

import lombok.Data;

@Data
public class VideosDTO implements ICity {


    /**
     * total : 120
     * per_page : 10
     * current_page : 1
     * last_page : 12
     * data : [{"id":120,"uid":1,"cid":"1","sid":1,"vkey":"b2a382ac051a22c92ef4857e04fcf126","feature":"1","title":"186496976920","description":null,"tags":"cute animals,animals,animal,cute cat,funny cat,cats,cat,catofday,catofinstagram,catoftheday,beautiful,sleep,video,funny,lol,lmao,so cute,cuteness,cute,animalwoonz,amazing,ked,kedi,kediler,kedi videoları","image":"https://apiv1.afuny.com/media/img/1.png","height":284,"width":176,"duration":12,"rating":7,"views":379,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/1.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/1.mp4/index.m3u8","commentnum":1},{"id":119,"uid":1,"cid":"1","sid":1,"vkey":"90fa4d7f783043d2a401b49a7d22fc06","feature":"1","title":"186497057698","description":null,"tags":"animals,animal,cute animals,Cute Dogs,doglover,dogs,dogstagram,dogsofinstagram,dog photos,dogoftheday,dog,funny,fun,so cute,sevimli,beautiful,nice,video,animal video","image":"https://apiv1.afuny.com/media/img/07e1cd7dca89a1678042477183b7ac3f.jpg","height":778,"width":496,"duration":10,"rating":20,"views":71,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/07e1cd7dca89a1678042477183b7ac3f.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/07e1cd7dca89a1678042477183b7ac3f.mp4/index.m3u8","commentnum":0},{"id":118,"uid":1,"cid":"1","sid":1,"vkey":"2662a530687aee02b41c5a8efb1ed5a7","feature":"1","title":"186497282795","description":null,"tags":"cute animals,animals,animal,animallovers,animallover,cute cat,funny cat,ninja cat,cats,catoftheday,cat,kedi,kediler,beautiful,cool,video,animal video","image":"https://apiv1.afuny.com/media/img/5ef059938ba799aaa845e1c2e8a762bd.jpg","height":720,"width":488,"duration":15,"rating":3,"views":35,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/5ef059938ba799aaa845e1c2e8a762bd.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/5ef059938ba799aaa845e1c2e8a762bd.mp4/index.m3u8","commentnum":0},{"id":117,"uid":1,"cid":"1","sid":1,"vkey":"80344d76b411a5b99447d255e65313f2","feature":"1","title":"186497440584","description":null,"tags":"animals,cute animals,animal,raven,funny videos,fun,funny,lol,lmao,komik video,so cute,cuteness,cute,bird,birds","image":"https://apiv1.afuny.com/media/img/eb160de1de89d9058fcb0b968dbbbd68.jpg","height":394,"width":460,"duration":30,"rating":0,"views":25,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/eb160de1de89d9058fcb0b968dbbbd68.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/eb160de1de89d9058fcb0b968dbbbd68.mp4/index.m3u8","commentnum":0},{"id":116,"uid":1,"cid":"1","sid":1,"vkey":"74891911246390a76dcc9f3e7ed8591a","feature":"1","title":"Elephant pranks people and then laughs it off","description":null,"tags":"animals,animal,cute animals,elephant,so cute,cuteness,cute,funny prank,prank,funny,fun,lol,lmao,animallover,pretty,meme,memes,9gag","image":"https://apiv1.afuny.com/media/img/c45147dee729311ef5b5c3003946c48f.jpg","height":236,"width":424,"duration":7,"rating":0,"views":18,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/c45147dee729311ef5b5c3003946c48f.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/c45147dee729311ef5b5c3003946c48f.mp4/index.m3u8","commentnum":0},{"id":115,"uid":1,"cid":"1","sid":1,"vkey":"e2d5aa130d53a67c23cceb34e3c5f1d5","feature":"1","title":"Tattoo Artist Goodmorningtown \\n For more: artwoonz.com","description":null,"tags":"","image":"https://apiv1.afuny.com/media/img/2b44928ae11fb9384c4cf38708677c48.jpg","height":600,"width":480,"duration":6,"rating":0,"views":10,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/2b44928ae11fb9384c4cf38708677c48.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/2b44928ae11fb9384c4cf38708677c48.mp4/index.m3u8","commentnum":0},{"id":114,"uid":1,"cid":"1","sid":1,"vkey":"31ca00b60e651fccdc5fae9feda81876","feature":"1","title":"187941396736","description":null,"tags":"cute animals,animals,animal,amazing video,amazing,animal video,so cute,cuteness,cute,black cat,cats,cat,kedi,kediler,funny cat,cat video,jump","image":"https://apiv1.afuny.com/media/img/5fd0b37cd7dbbb00f97ba6ce92bf5add.jpg","height":598,"width":480,"duration":8,"rating":1,"views":7,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/5fd0b37cd7dbbb00f97ba6ce92bf5add.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/5fd0b37cd7dbbb00f97ba6ce92bf5add.mp4/index.m3u8","commentnum":0},{"id":113,"uid":1,"cid":"1","sid":1,"vkey":"f6b3b802ace1c2e9cbc5ce8a38e60f21","feature":"1","title":"187941509771","description":null,"tags":"monkey,cute animals,animals,animal,evolution,animallovers,animallover,amazing video,amazing,so cute,cuteness,cute","image":"https://apiv1.afuny.com/media/img/73278a4a86960eeb576a8fd4c9ec6997.jpg","height":854,"width":592,"duration":8,"rating":0,"views":4,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/73278a4a86960eeb576a8fd4c9ec6997.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/73278a4a86960eeb576a8fd4c9ec6997.mp4/index.m3u8","commentnum":0},{"id":112,"uid":1,"cid":"1","sid":1,"vkey":"e2c664d57927484b192f5340df85cd8a","feature":"1","title":"187941555579","description":null,"tags":"cute animals,animals,animal,golden retriever,cute puppy,cuteness,so cute,cute,beautiful,Cute Dogs,dog photos,doglover,dogs,dogsofinstagram,dog,sevimli","image":"https://apiv1.afuny.com/media/img/7f6ffaa6bb0b408017b62254211691b5.jpg","height":854,"width":480,"duration":19,"rating":0,"views":4,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/7f6ffaa6bb0b408017b62254211691b5.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/7f6ffaa6bb0b408017b62254211691b5.mp4/index.m3u8","commentnum":0},{"id":111,"uid":1,"cid":"1","sid":1,"vkey":"396f24e2e2cd6c4f056b37b4b4a7bfc9","feature":"1","title":"186026992269","description":null,"tags":"dog,dogsofinstaworld,dogsofinstagram,dogstagram,Cute Dogs,funny dogs,love dogs,dogs of tumblr,dogs,catoftheday,catlover,funny cat,cats,cat,dog and cat,cute animal,cute animals,animals,animal,animallover,animal video","image":"https://apiv1.afuny.com/media/img/698d51a19d8a121ce581499d7b701668.jpg","height":574,"width":460,"duration":13,"rating":0,"views":3,"createtime":null,"updatetime":null,"deletetime":null,"status":"1","mp4url":"https://apiv1.afuny.com/media/mp4/698d51a19d8a121ce581499d7b701668.mp4","hlsurl":"https://apiv1.afuny.com/hls/media/mp4/698d51a19d8a121ce581499d7b701668.mp4/index.m3u8","commentnum":0}]
     * status : success
     * code : 200
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private String status;
    private String location;
    private int code;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String getCity() {
        return location;
    }

    public static class DataBean implements IPreMovieVO {

        /**
         * id : 120
         * uid : 1
         * cid : 1
         * sid : 1
         * vkey : b2a382ac051a22c92ef4857e04fcf126
         * feature : 1
         * title : 186496976920
         * description : null
         * tags : cute animals,animals,animal,cute cat,funny cat,cats,cat,catofday,catofinstagram,catoftheday,beautiful,sleep,video,funny,lol,lmao,so cute,cuteness,cute,animalwoonz,amazing,ked,kedi,kediler,kedi videoları
         * image : https://apiv1.afuny.com/media/img/1.png
         * height : 284
         * width : 176
         * duration : 12
         * rating : 7
         * views : 379
         * createtime : null
         * updatetime : null
         * deletetime : null
         * status : 1
         * mp4url : https://apiv1.afuny.com/media/mp4/1.mp4
         * hlsurl : https://apiv1.afuny.com/hls/media/mp4/1.mp4/index.m3u8
         * commentnum : 1
         */

        private int id;
        private int uid;
        private String cid;
        private int sid;
        private String vkey;
        private String feature;
        private String title;
        private String description;
        private String tags;
        private String image;
        private int height;
        private int width;
        private int duration;
        private String likes;
        private String views;
        private String avatar;
        private String author;
        private Object createtime;
        private Object updatetime;
        private Object deletetime;
        private String status;
        private String mp4url;
        private String hlsurl;
        private String commentnum;
        private Integer isvip;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getVkey() {
            return vkey;
        }

        public void setVkey(String vkey) {
            this.vkey = vkey;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getTitle() {
            return title;
        }


        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getHeight() {
            if(height <= 0)
                return 1920;
            return height;
        }

        @Override
        public String getMovieDesc() {
            return description != null ? description : "";
        }

        @Override
        public String getVideoKey() {
            return vkey;
        }

        @Override
        public String getVideo_commentnum() {
            return commentnum + "";
        }

        @Override
        public String getAvatar() {
            return avatar;
        }



        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String getWatchNum() {
            return views + "";
        }

        @Override
        public String getLoveNum() {
            return likes + "";
        }

        @Override
        public String getCollectNum() {
            return 0 + "";
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }



        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
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

        public String getMp4url() {
            return mp4url;
        }

        public void setMp4url(String mp4url) {
            this.mp4url = mp4url;
        }

        public String getHlsurl() {
            return hlsurl;
        }

        public void setHlsurl(String hlsurl) {
            this.hlsurl = hlsurl;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(String commentnum) {
            this.commentnum = commentnum;
        }

        @Override
        public String getMovieUri() {
            return mp4url;
        }

        @Override
        public String getPlaceImage() {
            return image;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getIsvip() {
            return isvip;
        }

        public void setIsvip(Integer isvip) {
            this.isvip = isvip;
        }

        @Override
        public Boolean videoIsVip() {
            return isvip > 0;
        }
    }
}
