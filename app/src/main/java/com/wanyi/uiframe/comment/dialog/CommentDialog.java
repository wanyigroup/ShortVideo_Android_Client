package com.wanyi.uiframe.comment.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wanyi.uiframe.R;
import com.wanyi.uiframe.aop.AopUserLogin;
import com.wanyi.uiframe.api.callback.ApiVideo;
import com.wanyi.uiframe.comment.CommitAdapter;
import com.wanyi.uiframe.comment.SoftKeyBoardListener;
import com.wanyi.uiframe.comment.SoftKeyHideShow;
import com.wanyi.uiframe.comment.action.ICommentItem;
import com.wanyi.uiframe.dialog.BaseDialogFragment;
import com.wanyi.uiframe.eventbus.ECommentUpdate;
import com.xuexiang.xui.widget.statelayout.SimpleAnimationListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class CommentDialog extends BaseDialogFragment {

    @BindView(R.id.recyclerViewCommit)
    RecyclerView recyclerViewCommit;
    @BindView(R.id.tv_context)
    TextView tv_context;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    @BindView(R.id.commit)
    View commit;
    @BindView(R.id.tv_shape)
    TextView tv_shape;
    @BindView(R.id.tv_shape2)
    TextView tv_shape2;
    @BindView(R.id.ll_cancel)
    LinearLayout ll_cancel;
    @BindView(R.id.et_context)
    EditText et_context;
    @BindView(R.id.all_comment)
    TextView all_comment;
    //dialog对话框
    CallBack callBack;

    /**
     * 视频主键
     */
    String videoKey;

    private SoftKeyBoardListener softKeyBoardListener;//软键盘监听

    private CommitAdapter commitAdapter;

    List<ICommentItem> dataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_comment;
    }

    @Override
    protected boolean isTouchHide() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSoftKeyBoardListener();
        showCommitDialog();
        callBack.dialogShow();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void show(FragmentManager manager, String tag, String videoKey) {
        super.show(manager, tag);
        this.videoKey = videoKey;
    }

    /**
     * 评论布局
     */
    public void showCommitDialog() {
        commitAdapter = new CommitAdapter(getContext(),dataList);
        recyclerViewCommit.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCommit.setAdapter(commitAdapter);
        ApiVideo.showCommentData(videoKey, source -> {
            dataList.clear();
            dataList.addAll(source);
            all_comment.setText(String.format("全部评论(%d)",source.size()));
            commitAdapter.notifyDataSetChanged();
        });
        //为布局设置显示的动画
        Animation showAction = AnimationUtils.loadAnimation(getContext(), R.anim.actionsheet_dialog_in);
        commit.startAnimation(showAction);

        //显示布局和阴影
        commit.setVisibility(View.VISIBLE);
        tv_shape.setVisibility(View.VISIBLE);
        //关闭评论
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideCommit();
                tv_shape.setVisibility(View.GONE);
            }
        });
        //阴影点击,隐藏评论列表和阴影
        tv_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideCommit();
            }
        });
        //输入评论点击
        tv_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
        //第二层阴影点击，隐藏输入评论框和软键盘
        tv_shape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
        //发送评论
        tv_send.setOnClickListener(new View.OnClickListener() {

            @AopUserLogin
            @Override
            public void onClick(View view) {
                String comment = et_context.getText().toString().trim();
                et_context.setText("");
                ApiVideo.showReplayStatus(videoKey,comment, isRight -> {
                    if(isRight) {
                        ApiVideo.showCommentData(videoKey, source -> {
                            dataList.clear();
                            dataList.addAll(source);
                            all_comment.setText(String.format("全部评论(%d)",source.size()));
                            commitAdapter.notifyDataSetChanged();
                        });
                        ECommentUpdate eCommentUpdate = new ECommentUpdate();
                        eCommentUpdate.setVideoKey(videoKey);
                        EventBus.getDefault().post(eCommentUpdate);
                    }
                });
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
    }



    //隐藏评论
    private void hideCommit() {
        Animation hideAction = AnimationUtils.loadAnimation(getContext(),R.anim.actionsheet_dialog_out);
        hideAction.setAnimationListener(new SimpleAnimationListener(){
            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
                super.onAnimationEnd(animation);
            }
        });
        rootView.startAnimation(hideAction);
    }

    /**
     * 软键盘监听
     */
    private void setSoftKeyBoardListener() {
        softKeyBoardListener = new SoftKeyBoardListener(getActivity());
        //软键盘状态监听
        softKeyBoardListener.setListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                //动态设置控件宽高
                ViewGroup.LayoutParams params = rl_bottom.getLayoutParams();
                rl_bottom.setPadding(0, 0, 0, height);
                rl_bottom.setLayoutParams(params);
                //当软键盘显示的时候
                rl_bottom.setVisibility(View.VISIBLE);
                tv_shape2.setVisibility(View.VISIBLE);

                et_context.setFocusable(true);
                et_context.setFocusableInTouchMode(true);
                et_context.setCursorVisible(true);
                et_context.requestFocus();
            }

            @Override
            public void keyBoardHide(int height) {
                //当软键盘隐藏的时候
                rl_bottom.setVisibility(View.GONE);
                tv_shape2.setVisibility(View.GONE);
                et_context.setFocusable(false);
                et_context.setFocusableInTouchMode(false);
                et_context.setCursorVisible(false);
            }
        });
        //设置点击事件,显示软键盘
        et_context.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        //防止EditText点击两次才获取到焦点
        et_context.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识
            int flag = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                flag++;
                if (flag == 2) {
                    flag = 0;//不要忘记这句话
                    //处理逻辑
                    et_context.setFocusable(true);
                    et_context.setFocusableInTouchMode(true);
                    et_context.setCursorVisible(true);
                }
                return false;
            }
        });
    }

    @Override
    public void dismiss() {
        dataList.clear();
        callBack.dialogHide();
        super.dismiss();
    }


    public interface CallBack {

        /**
         * 对话框隐藏
         */
        void dialogHide();

        /**
         * 对话框展示
         */
        void dialogShow();

    }



}
