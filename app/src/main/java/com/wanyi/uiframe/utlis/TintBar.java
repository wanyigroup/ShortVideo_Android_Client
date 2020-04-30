package com.wanyi.uiframe.utlis;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/*
 * @author: luckyShane
 * @date: 2019/3/6
 */
public class TintBar {
    private static final String TAG_FAKE_STATUS_BAR = "tint_fake_status_bar";
    private static final String TAG_OFFSET = "tint_offset";
    private static final int KEY_OFFSET = -1024;

    public static View setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return null;
        }
        makeStatusBarTransparent(activity);
        return applyStatusBarColor(activity, color);
    }

    public static View setStatusBarBackground(Activity activity, int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return null;
        }
        makeStatusBarTransparent(activity);
        return applyStatusBarBackground(activity, resId);
    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void clearStatusBarTransparent(Activity activity,int statusColor) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(statusColor);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    public static void addMarginTopEqualStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        view.setTag(TAG_OFFSET);
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset instanceof Boolean) {
            return;
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin + getStatusBarHeight(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(KEY_OFFSET, true);
    }


    public static void subtractMarginTopEqualStatusBarHeight(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset == null || !(Boolean) haveSetOffset) {
            return;
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin,
                layoutParams.topMargin - getStatusBarHeight(),
                layoutParams.rightMargin,
                layoutParams.bottomMargin);
        view.setTag(KEY_OFFSET, false);
    }

    private static View applyStatusBarColor(Activity activity, int color) {
        View fakeStatusBar = ensureStatusBar(activity);
        fakeStatusBar.setBackgroundColor(color);
        return fakeStatusBar;
    }

    private static View applyStatusBarBackground(Activity activity, int resId) {
        View fakeStatusBar = ensureStatusBar(activity);
        fakeStatusBar.setBackgroundResource(resId);
        return fakeStatusBar;
    }

    private static View ensureStatusBar(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.getWindow().getDecorView();
        View fakeStatusBar = parent.findViewWithTag(TAG_FAKE_STATUS_BAR);
        if (fakeStatusBar == null) {
            fakeStatusBar = createStatusBar(activity);
            fakeStatusBar.setTag(TAG_FAKE_STATUS_BAR);
            parent.addView(fakeStatusBar);
        }
        return fakeStatusBar;
    }

    private static View createStatusBar(Activity activity) {
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        statusBarView.setLayoutParams(layoutParams);
        return statusBarView;
    }

    public static int getStatusBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 用于外部提供statusBar的情况，比如DrawerLayout页面一般不采用代码创建statusBar，而是布局中添加statusBar，然后使用此方法给其增加状态栏高度
     */
    public static View setStatusBarBackground(Activity activity, View statusBar, int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return statusBar;
        }
        makeStatusBarTransparent(activity);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
        } else {
            layoutParams.height = getStatusBarHeight();
        }
        statusBar.setLayoutParams(layoutParams);
        statusBar.setBackgroundResource(resId);
        return statusBar;
    }

    public static void fitTitleBar(Activity activity, final View titleBar) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        makeStatusBarTransparent(activity);
        final ViewGroup.LayoutParams layoutParams = titleBar.getLayoutParams();
        assert layoutParams != null;
        if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT ||
                layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            titleBar.post(new Runnable() {
                @Override
                public void run() {
                    layoutParams.height = titleBar.getHeight() + getStatusBarHeight();
                    titleBar.setPadding(titleBar.getPaddingLeft(),
                            titleBar.getPaddingTop() + getStatusBarHeight(),
                            titleBar.getPaddingRight(),
                            titleBar.getPaddingBottom());
                }
            });
        } else {
            layoutParams.height += getStatusBarHeight();
            titleBar.setPadding(titleBar.getPaddingLeft(),
                    titleBar.getPaddingTop() + getStatusBarHeight(),
                    titleBar.getPaddingRight(),
                    titleBar.getPaddingBottom());
        }
    }


    public static void setStatusBarLightMode(Activity activity, boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            int option = window.getDecorView().getSystemUiVisibility();
            if (isLightMode) {
                option |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                option &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(option);
        }
    }


}
