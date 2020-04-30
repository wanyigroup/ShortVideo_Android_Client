package com.wanyi.uiframe.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.callback.RecyclerItemClick;
import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;
import com.wanyi.uiframe.api.model.dto.vo.ISearchTendencyVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TendencyHistoryAdapter extends BaseQuickAdapter<ISearchTendencyVO, TendencyHistoryAdapter.HoldView> {


    private RecyclerItemClick<ISearchHistoryVO> recyclerItemClick;
    public TendencyHistoryAdapter(List<ISearchTendencyVO> list) {
        super(R.layout.item_search_tendency, list);
    }

    public void setRecyclerItemClick(RecyclerItemClick<ISearchHistoryVO> recyclerItemClick) {
        this.recyclerItemClick = recyclerItemClick;
    }

    @Override
    protected void convert(@NonNull HoldView helper, ISearchTendencyVO item) {
         helper.trendIndex.setText((helper.getAdapterPosition() + 1) + "");
         helper.trendTitle.setText(item.getTitle());
         helper.trendNum.setText(item.getReading());
         helper.itemView.setOnClickListener(v-> {
             if(recyclerItemClick != null) {
                 recyclerItemClick.callback(item);
             }
         });
    }

    public class HoldView extends BaseViewHolder {
        @BindView(R.id.trend_index)
        TextView trendIndex;
        @BindView(R.id.trend_title)
        TextView trendTitle;
        @BindView(R.id.trend_num)
        TextView trendNum;

        public HoldView(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

    }



}
