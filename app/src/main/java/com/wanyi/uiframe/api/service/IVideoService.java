package com.wanyi.uiframe.api.service;


import com.wanyi.uiframe.api.model.dto.video.VideoCommentDTO;
import com.wanyi.uiframe.api.model.dto.video.VideoCommentResultDTO;
import com.wanyi.uiframe.api.model.dto.video.VideoHostSearchDTO;
import com.wanyi.uiframe.api.model.dto.video.VideoStatusDTO;
import com.wanyi.uiframe.api.model.dto.video.VideoValueDTO;
import com.wanyi.uiframe.api.model.dto.video.VideosDTO;
import io.reactivex.Observable;
import lombok.Generated;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IVideoService {

    /**
     * 暂无
     * @param page 页码
     * @return 视频列表
     */
    @Deprecated
    @GET("/api/video/lists")
    Observable<VideosDTO> list(@Query("page") Integer page);

    /**
     * 同城视频
     * @param page 页码
     * @return 视频列表
     */
    @GET("/api/video/location")
    Observable<VideosDTO> location(@Query("page") Integer page);

    /**
     * 获取当前城市
     * @return
     */
    @GET("/api/video/location")
    Observable<VideosDTO> city();


    /**
     * 推荐视频
     * @param page 页码
     * @return 视频列表
     */
    @GET("/api/video/feature")
    Observable<VideosDTO> feature(@Query("page") Integer page);

    /**
     * 关注视频
     * @param page 页码
     * @return 视频列表
     */
    @GET("/api/video/follow")
    Observable<VideosDTO> follow(@Query("page") Integer page);

    /**
     * 喜欢视频
     * @param video_key 视频标识
     * @return 返回值
     */
    @GET("/api/video/like/key/{key}")
    Observable<VideoValueDTO> rating(@Path("key") String video_key);

    /**
     * 更新访问量
     * @param video_key 视频标识
     * @return 返回值
     */
    @GET("/api/video/views/key/{key}")
    Observable<VideoValueDTO> update_visit(@Path("key") String video_key);

    /**
     * 收藏视频
     * @param video_key 视频标识
     * @return 视频得KEY
     */
    @POST("/api/video/fav")
    Observable<VideoStatusDTO> fav(@Query("key") String video_key);

    /**
     * 热门搜索关键词
     * @return 热门关键词
     */
    @GET("/api/video/hotkeyword")
    Observable<VideoHostSearchDTO> host_search();

    /**
     * 搜索视频
     * @param page 页码
     * @return 对应得值
     */
    @GET("/api/video/search/keyword/{keyword}")
    Observable<VideosDTO> keyword_search(@Path("keyword")String keyword ,@Query("page") Integer page);

    /**
     * 获取视频评论列表
     * @param video_key 视频主键
     * @return
     */
    @GET("/api/video/commentlist/key/{video_key}")
    Observable<VideoCommentDTO> comment_list(@Path("video_key") String video_key);

    /**
     * 评论内容
     * @param video_key video主键
     * @param comment 内容
     * @return
     */
    @POST("/api/video/commentcreate/key/{video_key}")
    Observable<VideoCommentResultDTO> send_comment(@Path("video_key") String video_key, @Query("content") String comment);




}
