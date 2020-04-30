package com.wanyi.uiframe.dkplayer.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.api.callback.ApiVideo;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.dkplayer.adapter.callback.TikTokCallBack;
import com.wanyi.uiframe.dkplayer.util.cache.PreloadManager;
import com.wanyi.uiframe.eventbus.ECommentUpdate;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.singleton.EnumPreMovie;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;


public class Tiktok3DetailAdapter extends RecyclerView.Adapter<Tiktok3DetailAdapter.ViewHolder> {

    /**
     * 数据源
     */
    private List<IPreMovieVO> mVideoBeans;

    /**
     * 视频更新
     */
    private HashMap<String, Tiktok3DetailAdapter.ViewHolder> videoKeyHash = new HashMap<>();

    /**
     * 反馈
     */
    private TikTokCallBack callBack;

    /**
     * 是否展示下载框
     */
    private Boolean isShowDown = false;

    public void setCallBack(TikTokCallBack callBack) {
        this.callBack = callBack;
    }

    public Tiktok3DetailAdapter(List<IPreMovieVO> videoBeans) {
        this.mVideoBeans = videoBeans;
        EventBus.getDefault().register(this);
    }

    @NonNull
    @Override
    public Tiktok3DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tik_tok_detail, parent, false);
        return new Tiktok3DetailAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull Tiktok3DetailAdapter.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        final IPreMovieVO item = mVideoBeans.get(position);
        videoKeyHash.put(item.getVideoKey(),holder);
        //开始预加载
        PreloadManager.getInstance(context).addPreloadTask(item.getMovieUri(), position);
        holder.mTitle.setText(item.getTitle());
        holder.tvAuthor.setText(item.getAuthor());
        holder.mPosition = position;
        holder.ivAttention.setOnClickListener(v -> {
            attentionAnim(context,holder.ivAttention,holder.ivAttentionBg);
        });
        holder.tvLikes.setText(item.getLoveNum());
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                ApiVideo.showLikeNumber(item.getVideoKey(), likeValue -> {
                    holder.tvLikes.setText(likeValue);
                });
            }

            @Override
            public void unLiked(LikeButton likeButton) {

            }
        });
        holder.tvComment.setText(item.getVideo_commentnum());
        holder.tvComment.setOnClickListener((v) -> {
            if(callBack != null) {
                callBack.callBack(item.getVideoKey());
            }
        });
        holder.tvDownload.setOnClickListener((v) -> {
            if(callBack != null) {
                callBack.downLoad(item);
            }
        });
        Glide.with(context).load(item.getPlaceImage()).apply(RequestOptions.noTransformation().placeholder(R.color.white)).into(holder.thumbImage);
        Glide.with(context).load(item.getAvatar()).apply(RequestOptions.noTransformation().placeholder(R.color.white)).into(holder.ivHead);
        if(isShowDown) {
            holder.tvDownload.setVisibility(View.VISIBLE);
        } else {
            holder.tvDownload.setVisibility(View.GONE);
        }
        if(DynamicMoviePresenter.getInstance().getDataSource()== EnumPreMovie.mycache) {
            holder.tvLikes.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvDownload.setVisibility(View.GONE);
            holder.tvReprinted.setVisibility(View.GONE);
            holder.ivHead.setVisibility(View.GONE);
            holder.ivAttention.setVisibility(View.GONE);
            holder.ivAttentionBg.setVisibility(View.GONE);
            holder.mTitle.setVisibility(View.GONE);
            holder.tvAuthor.setVisibility(View.GONE);
            holder.likeButton.setVisibility(View.GONE);
        }
    }

    public void setShowDown(Boolean showDown) {
        this.isShowDown = showDown;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCommentNumber(ECommentUpdate item) {
        TextView tvComment = videoKeyHash.get(item.getVideoKey()).tvComment;
        try {
            Integer comment_num =  Integer.parseInt(tvComment.getText().toString().trim());
            if(comment_num < 999) {
                tvComment.setText(comment_num + 1 + "");
            }
        }catch (NumberFormatException ex) {
            //有异常，截获，不处理
        }

    }

    /**
     * 关注动画
     * @param ivAttention
     * @param ivAttentionBg
     */
    private void attentionAnim(Context mContext,ImageView ivAttention, ImageView ivAttentionBg) {
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention);
        AnimatorSet animator2 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention2);
        AnimatorSet animator3 = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention3);
        AnimatorSet animatorbg = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.attention3);
        animator.setTarget(ivAttention);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ivAttention.setImageResource(R.mipmap.hook_red);
                animator2.setTarget(ivAttention);
                animator2.start();
            }
        });
        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator3.setTarget(ivAttention);
                animatorbg.setTarget(ivAttentionBg);
                animator3.start();
                animatorbg.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });
    }


    @Override
    public void onViewDetachedFromWindow(@NonNull Tiktok3DetailAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        IPreMovieVO item = mVideoBeans.get(holder.mPosition);
        //取消预加载
        PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(item.getMovieUri());

    }

    @Override
    public int getItemCount() {
        return mVideoBeans != null ? mVideoBeans.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public int mPosition;
        public TextView mTitle;//标题
        public TextView tvLikes;//喜欢
        public TextView tvComment;//评论
        public TextView tvDownload;
        public FrameLayout mPlayerContainer;
        public ImageView ivHead,ivAttention,ivAttentionBg;
        public LikeButton likeButton;
        public ImageView thumbImage;
        public TextView tvAuthor;
        public TextView tvReprinted;

        ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tv_title);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvDownload = itemView.findViewById(R.id.mp_download);
            mPlayerContainer = itemView.findViewById(R.id.container);
            ivHead = itemView.findViewById(R.id.ivHead);
            ivAttention = itemView.findViewById(R.id.ivAttention);
            ivAttentionBg = itemView.findViewById(R.id.ivAttentionBg);
            likeButton = itemView.findViewById(R.id.btLikes);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            thumbImage = itemView.findViewById(R.id.thumb_image);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvReprinted = itemView.findViewById(R.id.tvReprinted);
            itemView.setTag(this);
        }
    }




}
