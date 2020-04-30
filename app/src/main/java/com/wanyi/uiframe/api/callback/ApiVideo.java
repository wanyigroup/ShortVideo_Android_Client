package com.wanyi.uiframe.api.callback;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.comment.action.ICommentItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ApiVideo {


    /**
     * 显示数据评论
     * @param videoKey 视频主键
     * @param consumer 消费得数据
     */
    public static void showCommentData(String videoKey,Consumer<List<ICommentItem>> consumer) {
        ApiFacade.createVideo().map(iVideoService -> iVideoService.comment_list(videoKey).blockingFirst())
                .map(videoCommentDTO -> {
                    List<ICommentItem> dataList = new ArrayList<>();
                    dataList.addAll(videoCommentDTO.getData().getData());
                    return dataList; })
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

//    /**
//     * 获取评论数量
//     * @param videoKey 视频主键
//     * @param consumer 视频数据
//     */
//    public static void showCommentNumber(String videoKey,Consumer<Integer> consumer) {
//        ApiFacade.createVideo().map(iVideoService -> iVideoService.comment_list(videoKey).blockingFirst())
//                .map(videoCommentDTO -> {
//                    List<ICommentItem> dataList = new ArrayList<>();
//                    dataList.addAll(videoCommentDTO.getData().getData());
//                    return dataList.size(); })
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);
//    }

    /**
     * 显示回复是否成功
     * @param videoKey 视频主键
     * @param consumer 回复是否成功
     */
    public static void showReplayStatus(String videoKey,String comment,Consumer<Boolean> consumer) {
        ApiFacade.createVideo().map(iVideoService -> iVideoService.send_comment(videoKey,comment).blockingFirst().isSuccess())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 显示喜欢得数量
     * @param videoKey 主键
     * @param likeValue 喜欢得值
     */
    public static void showLikeNumber(String videoKey,Consumer<String> likeValue){
        ApiFacade.createVideo().map(iVideoService -> iVideoService.rating(videoKey).blockingFirst().getValue())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(likeValue);
    }










}
