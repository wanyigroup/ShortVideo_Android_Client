package com.wanyi.uiframe.adapter;

import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.persistence.action.INoticeVO;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;



import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerMessageAnnouncementAdapter extends BaseQuickAdapter<INoticeVO, RecyclerMessageAnnouncementAdapter.HoldView> {

    private CallBack callBack;


    public RecyclerMessageAnnouncementAdapter() {
        super(R.layout.item_mymessage_announcement, new ArrayList<>());
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected void convert(@NonNull HoldView helper, INoticeVO item) {
      //  Glide.with(mContext).load(item.getUri()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(helper.announcementPhoto);
        helper.announcementTitle.setText(item.getTitle());
        Spanned htmlText = Html.fromHtml(StringEscapeUtils.unescapeHtml3(item.getContent()));
        helper.announcementContent.setText(htmlText);
        helper.announcementContent.setEnabled(false);
        helper.announcementTime.setText(item.getDate());
        helper.announcementContent.setOnTouchListener(new LinkMovementMethodOverride());
        helper.announcementContent.setScrollContainer(false);
        if(item.isRead()) {
            helper.center_dot.setVisibility(View.GONE);
        }else {
            helper.center_dot.setVisibility(View.VISIBLE);
        }
        helper.itemView.setOnClickListener((v) -> {
            if(callBack != null) {
                callBack.clickMessage(item.getTitle(),htmlText,item);
                notifyItemChanged(helper.getAdapterPosition());
            }
        });

    }


    public class HoldView extends BaseViewHolder {

        @BindView(R.id.announcement_photo)
        ImageView announcementPhoto;
        @BindView(R.id.announcement_title)
        TextView announcementTitle;
        @BindView(R.id.announcementContent)
        AutoFitTextView announcementContent;
        @BindView(R.id.announcementTime)
        TextView announcementTime;
        @BindView(R.id.center_dot)
        View center_dot;

        public HoldView(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }


    public interface  CallBack {
        void clickMessage(String title,Spanned  htmlText,INoticeVO noticeVO);
    }


    public static class LinkMovementMethodOverride implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView widget = (TextView) v;
            Object text = widget.getText();
            if (text instanceof Spanned) {
                Spanned buffer = (Spanned) text;

                int action = event.getAction();

                if (action == MotionEvent.ACTION_UP
                        || action == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    x -= widget.getTotalPaddingLeft();
                    y -= widget.getTotalPaddingTop();

                    x += widget.getScrollX();
                    y += widget.getScrollY();

                    Layout layout = widget.getLayout();
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);
                    /**
                     * 下面这几行就是解决图片点击错位的
                     */
                    float xLeft=layout.getPrimaryHorizontal(off);
                    if(xLeft<x){
                        off+=1;
                    }else{
                        off-=1;
                    }

                    ClickableSpan[] link = buffer.getSpans(off, off,
                            ClickableSpan.class);

                    if (link.length != 0) {
                        if (action == MotionEvent.ACTION_UP) {
                            link[0].onClick(widget);
                        } else if (action == MotionEvent.ACTION_DOWN) {
                            // Selection only works on Spannable text. In our case setSelection doesn't work on spanned text
                            //Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                        }
                        return true;
                    }
                }

            }

            return false;
        }

    }
}
