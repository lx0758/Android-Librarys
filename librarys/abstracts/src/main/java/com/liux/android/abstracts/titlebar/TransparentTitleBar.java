package com.liux.android.abstracts.titlebar;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.liux.android.abstracts.util.FixFullScreenAndResize;
import com.liux.android.abstracts.util.TitleBarUtil;

import java.util.IllegalFormatFlagsException;

/**
 * 透明的{@link TitleBar}实现,开启沉浸式状态栏,隐藏标题栏 <br>
 *
 * http://www.jianshu.com/p/f02abf30fc82 <br>
 * http://www.jianshu.com/p/140be70b84cd <br>
 * https://juejin.im/post/5989ded56fb9a03c3b6c8bde
 */
public class TransparentTitleBar extends TitleBar {

    public TransparentTitleBar(AppCompatActivity activity) {
        super(activity);

        // 设置AppCompatActivity无ToolBar
        activity.getDelegate().requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置状态栏和导航栏完全透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 清除 KITKAT 的透明设定
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            // 开启系统状态栏颜色设置
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // 设定系统状态栏颜色
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            // activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);

            // 全屏状态，视觉上的作用和WindowManager.LayoutParams.FLAG_FULLSCREEN一样
            // View.SYSTEM_UI_FLAG_FULLSCREEN
            // 这两个属性并不会真正隐藏状态栏或者导航栏，只是把整个content的可布局区域延伸到了其中
            // View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            // 此View一般和上面几个提到的属性一起使用，它可以保证在系统控件隐藏显示时，不会让本view重新layout
            // View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void setup(ActionBar actionBar) {
        if (actionBar != null) {
            throw new IllegalFormatFlagsException("The window style do not contain Window.FEATURE_NO_TITLE");
        }

        FixFullScreenAndResize.fix(getActivity());

        int topPadding = TitleBarUtil.getTransparentStatusBarHeight(getActivity());
        onInitView(topPadding);
    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public void setTitleColor(int color) {

    }

    /**
     * 初始化 TitleBar 的View
     * @param topPadding
     */
    public void onInitView(int topPadding) {

    }
}