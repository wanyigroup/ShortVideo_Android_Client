package com.wanyi.uiframe.dkplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.callback.ItemClickCallback;
import com.wanyi.uiframe.dkplayer.activity.tiktok.TikTok3Activity;
import com.wanyi.uiframe.dkplayer.fragment.TikTokFullFragment;
import com.wanyi.uiframe.dkplayer.widget.ScaleImageView;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import com.xuexiang.xui.widget.button.ButtonView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TikTokListAdapter extends BaseQuickAdapter<IPreMovieVO,TikTokListAdapter.TikTokListViewHolder> {

    private List<IPreMovieVO> data;
    private ItemClickCallback clickCallback;

    public TikTokListAdapter( @Nullable List<IPreMovieVO> data) {
        super(R.layout.item_tiktok_list, data);
        this.data = data;
    }

    /**
     * 单击回调
     * @param clickCallback
     */
    public void setClickCallback(ItemClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    @Override
    protected void convert(@NonNull TikTokListViewHolder helper, IPreMovieVO item) {
        helper.mTitle.setText(item.getMovieDesc());
        helper.mThumb.setInitSize(item.getWidth(), item.getHeight());
        helper.videoRating.setText(item.getLoveNum());
        helper.videoViews.setText(item.getWatchNum());
        RequestOptions requestOptions = RequestOptions.noTransformation().error(R.mipmap.place_holder_error);
        Glide.with(helper.mThumb.getContext()).applyDefaultRequestOptions(requestOptions)
                .load(item.getPlaceImage())
                .into(helper.mThumb);
        helper.mPosition = helper.getAdapterPosition();
        helper.itemView.setOnClickListener(v -> {
            int position = helper.getAdapterPosition();
            if(clickCallback != null) {
                clickCallback.itemClick(position);
            }
        });
        if(DynamicMoviePresenter.getInstance().getDataSource() == EnumPreMovie.mycache){
            helper.videoRating.setVisibility(View.GONE);
            helper.videoViews.setVisibility(View.GONE);
        }
    }

    /**
     * 搜索操作
     * @param position 位置
     * @param context 上下文
     */
    protected void skipOperation(Integer position,Context context) {
        DynamicMoviePresenter.getInstance().record(position);
        context.startActivity(new Intent(context, TikTok3Activity.class));
    }


    public static class TikTokListViewHolder extends BaseViewHolder {

        public ScaleImageView mThumb;
        public TextView mTitle;
        @BindView(R.id.video_rating)
        ButtonView videoRating;
        @BindView(R.id.video_views)
        ButtonView videoViews;
        public int mPosition;

        public TikTokListViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumb = itemView.findViewById(R.id.iv_thumb);
            mTitle = itemView.findViewById(R.id.tv_title);
            ButterKnife.bind(this,itemView);
        }
    }





}
