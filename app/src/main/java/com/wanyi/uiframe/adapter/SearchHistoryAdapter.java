package com.wanyi.uiframe.adapter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.callback.RecyclerItemClick;
import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchHistoryAdapter extends BaseQuickAdapter<ISearchHistoryVO, SearchHistoryAdapter.HoldView> {


    private RecyclerItemClick<ISearchHistoryVO> recyclerItemClick;


    public SearchHistoryAdapter(List<ISearchHistoryVO> dataList) {
        super(R.layout.item_search_history,dataList);
    }

    public void setRecyclerItemClick(RecyclerItemClick<ISearchHistoryVO> recyclerItemClick) {
        this.recyclerItemClick = recyclerItemClick;
    }

    @Override
    protected void convert(@NonNull HoldView helper, ISearchHistoryVO item) {
        helper.tvRecord.setText(item.getTitle());
        helper.tvRecord.setOnClickListener(v -> {
            if(recyclerItemClick != null) {
                Log.e(getClass().getName(),"结果回调");
                recyclerItemClick.callback(item);
            }
        });
    }

    public class HoldView extends BaseViewHolder {
        @BindView(R.id.tv_record)
        TextView tvRecord;
        public HoldView(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

}
