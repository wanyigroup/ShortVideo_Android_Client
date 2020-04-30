package com.wanyi.uiframe.fragment.container;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.fragment.container.web.impl.AdvertiseWebFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import butterknife.BindView;

@Page(name = "系统通知", anim = CoreAnim.fade)
public class MessageFragment extends BaseFragment {

    public static String TITLE;
    public static Spanned CONTENT;

    @BindView(R.id.tv_context)
    TextView tvContext;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_container_message;
    }

    @Override
    protected void initViews() {
        Log.e(getClass().getName(),"content:" + CONTENT);
        Spanned sp = CONTENT;
        tvContext.setMovementMethod(LinkMovementMethod.getInstance());
        URLSpan[] urls = sp.getSpans(0, sp.length(), URLSpan.class);
        SpannableStringBuilder style = new SpannableStringBuilder(sp);
        style.clearSpans();
        for (final URLSpan url : urls) {
            //最主要的一点
            CustomClickUrlSpan myURLSpan = new CustomClickUrlSpan(url.getURL(), new OnLinkClickListener() {
                @Override
                public void onLinkClick(View view) {
                    AdvertiseWebFragment.URL = url.getURL();
                    openNewPage(AdvertiseWebFragment.class);
                }
            });
            style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tvContext.setText(style);
    }


    @Override
    protected String getPageTitle() {
        return TITLE;
    }

    public class CustomClickUrlSpan extends ClickableSpan {
        private String url;
        private OnLinkClickListener mListener;

        public CustomClickUrlSpan(String url, OnLinkClickListener listener) {
            this.url=url;
            this.mListener=listener;
        }

        @Override
        public void onClick(View widget) {
            if (mListener!=null){
                mListener.onLinkClick(widget);
            }
        }


    }

    /**
     * 跳转链接接口
     */
    public  interface OnLinkClickListener{
        void onLinkClick(View view);
    }
}
