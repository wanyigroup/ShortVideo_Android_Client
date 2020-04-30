package com.wanyi.uiframe.dkplayer.fragment.factorylist;

import com.wanyi.uiframe.dkplayer.fragment.TikTokListFragment;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.mvp.presenter.action.IDynamicMovieAction;
import com.wanyi.uiframe.singleton.EnumPreMovie;

public class SameCityTikTokListFragment extends TikTokListFragment {

    @Override
    protected IDynamicMovieAction getDataSource() {

        return DynamicMoviePresenter.getInstance().getDataSource(EnumPreMovie.samecity);
    }

    @Override
    protected void setDataSource() {
        DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.samecity);
    }


}
