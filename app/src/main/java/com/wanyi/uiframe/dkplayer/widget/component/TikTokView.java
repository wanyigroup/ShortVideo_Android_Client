package com.wanyi.uiframe.dkplayer.widget.component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.BaseIjkVideoView;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.wanyi.uiframe.R;

public class TikTokView extends BaseVideoController implements SeekBar.OnSeekBarChangeListener{

    private ImageView thumb;
    private ImageView mPlayBtn;

    private boolean mIsDragging;
    private int mScaledTouchSlop;
    private int mStartX, mStartY;
    private SeekBar mVideoProgress;

    public TikTokView(@NonNull Context context) {
        super(context);
    }

    public TikTokView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TikTokView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_tiktok_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        thumb = mControllerView.findViewById(R.id.iv_thumb);
        mPlayBtn = mControllerView.findViewById(R.id.play_btn);
        mVideoProgress = mControllerView.findViewById(R.id.seekBar);
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mVideoProgress.setOnSeekBarChangeListener(this);
        mVideoProgress.setVisibility(View.INVISIBLE);
        setOnClickListener(v -> {
            if(mMediaPlayer.isPlaying()){
               mediaPause();
            }else {
              mediaStart();
            }
        });

    }

    public void mediaStart(){
        mMediaPlayer.start();
    }

    public void mediaPause() {
        mMediaPlayer.pause();
    }


    @Override
    protected int setProgress() {
        if (mMediaPlayer == null || mIsDragging) {
            return 0;
        }

        int position = (int) mMediaPlayer.getCurrentPosition();
        int duration = (int) mMediaPlayer.getDuration();
        if(duration > 60 * 1000) {
            mVideoProgress.setVisibility(View.VISIBLE);
        } else {
            mVideoProgress.setVisibility(View.INVISIBLE);
        }
       // Log.e(getClass().getName(),"position:" + position + " duration:" + duration);
        if (mVideoProgress != null) {
            if (duration > 0) {
                mVideoProgress.setEnabled(true);
                int pos = (int) (position * 1.0 / duration * mVideoProgress.getMax());
                mVideoProgress.setProgress(pos);
            } else {
                mVideoProgress.setEnabled(false);
            }
            int percent = mMediaPlayer.getBufferedPercentage();
            if (percent >= 95) { //解决缓冲进度不能100%问题
                mVideoProgress.setSecondaryProgress(mVideoProgress.getMax());
            } else {
                mVideoProgress.setSecondaryProgress(percent * 10);
            }
        }
        return position;
    }

    /**
     * 解决点击和VerticalViewPager滑动冲突问题
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) event.getX();
                mStartY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                int endX = (int) event.getX();
                int endY = (int) event.getY();
                if (Math.abs(endX - mStartX) < mScaledTouchSlop
                        && Math.abs(endY - mStartY) < mScaledTouchSlop) {
                    performClick();
                }
                break;
        }
        return false;
    }


    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE:
                thumb.setVisibility(VISIBLE);
                break;
            case  IjkVideoView.STATE_PLAYING:
                post(mShowProgress);
                thumb.setVisibility(GONE);
                mPlayBtn.setVisibility(GONE);
                break;
            case  IjkVideoView.STATE_PAUSED:
                thumb.setVisibility(GONE);
                mPlayBtn.setVisibility(VISIBLE);
                break;
            case  IjkVideoView.STATE_PREPARED:
                break;
            case  IjkVideoView.STATE_ERROR:
                Toast.makeText(getContext(), R.string.dkplayer_error_message, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 重写network登录的样式
     */
    @Override
    public void showStatusView() {
        this.removeView(mStatusView);
        hideStatusView();
        BaseIjkVideoView.IS_PLAY_ON_MOBILE_NETWORK = true;
        mMediaPlayer.start();
        this.addView(mStatusView);
    }


    public ImageView getThumb() {
        return thumb;
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mIsDragging = true;
        removeCallbacks(mShowProgress);
        removeCallbacks(mFadeOut);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        long duration = mMediaPlayer.getDuration();
        long newPosition = (duration * seekBar.getProgress()) / mVideoProgress.getMax();
        mMediaPlayer.seekTo((int) newPosition);
        mIsDragging = false;
        post(mShowProgress);
        show();
    }
}
