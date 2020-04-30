package com.wanyi.uiframe.dkplayer.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.dkplayer.activity.tiktok.SearchTikTokActivity;
import com.wanyi.uiframe.mvp.presenter.action.factory.SearchMovieAction;

import java.util.List;

public class TikTokSearchListAdapter extends TikTokListAdapter {


    public TikTokSearchListAdapter(@Nullable List<IPreMovieVO> data) {
        super(data);
    }

    @Override
    protected void skipOperation(Integer position, Context context) {
        SearchMovieAction.getInstance().record(position);
        context.startActivity(new Intent(context, SearchTikTokActivity.class));
    }

}
