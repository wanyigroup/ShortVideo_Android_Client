package com.wanyi.uiframe.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.comment.action.ICommentItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: wu
 * date: on 2018/5/3.
 * describe:评论
 */

public class CommitAdapter extends RecyclerView.Adapter<CommitAdapter.MyViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private List<ICommentItem> commentItems;

    public interface OnItemClickListener {
        void onItemClick(int position, String Url);
    }

    public OnItemClickListener mOnItemClickListerer;

    public void setmOnItemClickListerer(OnItemClickListener listerer) {
        this.mOnItemClickListerer = listerer;
    }

    public CommitAdapter(Context context, List<ICommentItem> commentItems) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.commentItems = commentItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_video_commit, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ICommentItem item = commentItems.get(position);
        holder.tvName.setText(item.getNickName());
        holder.tvContext.setText(item.getComment());
        holder.tvTime.setText(item.getCreateTime());
    }



    @Override
    public int getItemCount() {
        return commentItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_context)
        TextView tvContext;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
